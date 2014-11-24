package com.innverse.elearn.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.innverse.elearn.contants.PGConstants;
import com.innverse.elearn.dao.PaymentDaoImpl;
import com.innverse.elearn.json.CheckoutForm;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserPayment;
import com.innverse.elearn.paypal.express.checkout.ECDoExpressCheckout;
import com.innverse.elearn.paypal.express.checkout.ECGetExpressCheckout;
import com.innverse.elearn.paypal.express.checkout.ECSetExpressCheckout;
import com.innverse.elearn.util.DateTimeUtils;

public class PaymentTransactionServiceImpl {

	private ECSetExpressCheckout ppSetEC;
	private ECGetExpressCheckout ppGetEC;
	private ECDoExpressCheckout ppDoEC;
	
	
	public ECSetExpressCheckout getPpSetEC() {
		return ppSetEC;
	}

	public void setPpSetEC(ECSetExpressCheckout ppSetEC) {
		this.ppSetEC = ppSetEC;
	}

	public ECGetExpressCheckout getPpGetEC() {
		return ppGetEC;
	}

	public void setPpGetEC(ECGetExpressCheckout ppGetEC) {
		this.ppGetEC = ppGetEC;
	}

	public ECDoExpressCheckout getPpDoEC() {
		return ppDoEC;
	}

	public void setPpDoEC(ECDoExpressCheckout ppDoEC) {
		this.ppDoEC = ppDoEC;
	}



	@Autowired
	private PaymentDaoImpl paymentDao;
	
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	public boolean initPPTransaction(CheckoutForm checkout, HttpServletRequest request){
		try{
			Member currentUser = (Member)request.getSession(false).getAttribute("member");
			
			
			UserPayment payment = new UserPayment();
			payment.setSellerAmount(checkout.getAmount());
			payment.setProfileId(currentUser.getAccount().getId());
			payment.setOrganizationId(currentUser.getAccount().getOrganization().getId());
			payment.setItemName(checkout.getPlanName());
			payment.setPaymentGateway(PGConstants.ppPaymentGateway);
			double totalAmount = checkout.getAmount();
			payment.setTotalAmount(totalAmount);
			payment.setCurrencyCode(PGConstants.currencyCode);
			Member memberAccount = (Member)request.getSession(false).getAttribute("member");
			payment.setUsername(memberAccount.getAccount().getUsername());
			payment.setPaymentType(PGConstants.paymentType);
			
			Map<String,String> result = null;
			String rURL = "http://"+request.getHeader("Host")+""+PGConstants.host + PGConstants.returnURL;
			String cURL = "http://"+request.getHeader("Host")+""+PGConstants.host + PGConstants.cancelURL;
			result = ppSetEC.mECSetExpressCheckout(rURL, cURL, String.valueOf(totalAmount), PGConstants.paymentType, PGConstants.currencyCode, checkout.getPlanName());
			
			System.out.println("SET EC ---> " + result.toString());
			System.out.println("PP TOKEN --->  " + result.get("TOKEN"));
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				request.getSession(false).setAttribute("PPTOKEN", result.get("TOKEN"));
				System.out.println("RETRIEVE SESSION PP TOKEN ---> " + request.getSession().getAttribute("PPTOKEN"));

				timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
				Calendar currentTime = DateTimeUtils.getGMTCalendar();
				payment.setTransTime(timeFormat.format(currentTime.getTime()));
				payment.setTimeZone("GMT");
				payment.setToken(result.get("TOKEN"));
				payment.setDateTime(new Date());
				paymentDao.savePaymentTransaction(payment);
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean getDetailPPTransaction(String token,HttpServletRequest request){
		try{
			Map<String,String> result = ppGetEC.mECGetExpressCheckout(token);
			System.out.println("GET EC ----> " + result.toString());
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				 UserPayment ppTransaction = paymentDao.getUserPaymentByToken(token);
				 ppTransaction.setPayerId(result.get("PAYERID"));
				 ppTransaction.setTransStatus(result.get("PAYERSTATUS"));
				 paymentDao.mergePaymentTransaction(ppTransaction);
				 request.getSession(false).setAttribute("PPTrans", ppTransaction);
				 return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

		public UserPayment commitPPTransaction(HttpServletRequest request){
		UserPayment ppTransaction = null;
		try{
			 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
			UserPayment userPayment =  (UserPayment)request.getSession().getAttribute("PPTrans");
			Map<String,String> result = ppDoEC.mECDoExpressCheckout(userPayment.getToken(), userPayment.getPayerId(), String.valueOf(userPayment.getTotalAmount()) , userPayment.getPaymentType(), userPayment.getCurrencyCode());
			System.out.println("DO EC ---> " + result.toString());
			if(result != null && result.get("ACK").equalsIgnoreCase("SUCCESS")){
				 ppTransaction = paymentDao.getUserPaymentByToken(userPayment.getToken());
				 ppTransaction.setTransactionID(result.get("TRANSACTIONID"));
				 ppTransaction.setTransStatus(result.get("PAYMENTSTATUS"));
				 ppTransaction.setFeeAmount(Double.valueOf(result.get("FEEAMT")));
				 ppTransaction.setSellerAmount(ppTransaction.getTotalAmount() - Double.valueOf(result.get("FEEAMT")));
				 ppTransaction.setORDERTIME(result.get("ORDERTIME"));
				 ppTransaction.setTIMESTAMP(result.get("TIMESTAMP"));
				 
				 timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
				 Calendar currentTime = DateTimeUtils.getGMTCalendar();
				 
				ppTransaction.setTransTime(timeFormat.format(currentTime.getTime()));
				ppTransaction.setTimeZone("GMT");

				paymentDao.finalPaymentTransaction(ppTransaction);
				 return ppTransaction;
			}else{
				return ppTransaction;
			}
		}catch(Exception e){
			e.printStackTrace();
			return ppTransaction;
		}
	}
	
}
