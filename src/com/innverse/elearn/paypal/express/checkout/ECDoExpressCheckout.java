package com.innverse.elearn.paypal.express.checkout;

import java.util.Map;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;

public class ECDoExpressCheckout {

	private NVPCallerServices caller = null;
	

	public String ECDoExpressCheckoutCode(String token,String payerID,String amount,
		String paymentType,String currencyCode)
	{
		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		
		try
		{
			
			caller = new NVPCallerServices();
			APIProfile profile = ProfileFactory.createSignatureAPIProfile();
			
			/* WARNING: Do not embed plaintext credentials in your application code.
			 Doing so is insecure and against best practices.
			 Your API credentials must be handled securely. Please consider
			 encrypting them for use in any production environment, and ensure
			 that only authorized individuals may view or modify them.*/
			 

		// 	Set up your API credentials, PayPal end point, API operation and version.
			profile.setAPIUsername("smrgrover2-facilitator_api1.gmail.com");
			profile.setAPIPassword("1386308605");
			profile.setSignature("Ai1PaghZh5FmBLCDCTQpwG8jB264AQEVXUkGZMk-Fpe2pFJXiCD5ECNj");
			profile.setEnvironment("sandbox");
			profile.setSubject("");
			caller.setAPIProfile(profile);
			encoder.add("VERSION", "51.0");
			encoder.add("METHOD","DoExpressCheckoutPayment");

		// Add request-specific fields to the request string.
			// Pass the token value by actual value returned in the SetExpressCheckout.
			encoder.add("TOKEN",token);
			encoder.add("PAYERID",payerID);
			encoder.add("AMT",amount);
			encoder.add("PAYMENTACTION",paymentType);
			encoder.add("CURRENCYCODE",currencyCode);
			encoder.add("SOLUTIONTYPE", "SOLE");
		// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode();
			String NVPResponse =caller.call(NVPRequest);
			decoder.decode(NVPResponse);
			
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return decoder.get("ACK");
	}
	
	public Map mECDoExpressCheckout(String token,String payerID,String amount,
			String paymentType,String currencyCode)
		{
			NVPEncoder encoder = new NVPEncoder();
			NVPDecoder decoder = new NVPDecoder();
			
			try
			{
				
				caller = new NVPCallerServices();
				APIProfile profile = ProfileFactory.createSignatureAPIProfile();
				
				/* WARNING: Do not embed plaintext credentials in your application code.
				 Doing so is insecure and against best practices.
				 Your API credentials must be handled securely. Please consider
				 encrypting them for use in any production environment, and ensure
				 that only authorized individuals may view or modify them.*/
				 

			// Set up your API credentials, PayPal end point, API operation and version.
				profile.setAPIUsername("smrgrover2-facilitator_api1.gmail.com");
				profile.setAPIPassword("1386308605");
				profile.setSignature("Ai1PaghZh5FmBLCDCTQpwG8jB264AQEVXUkGZMk-Fpe2pFJXiCD5ECNj");
				profile.setEnvironment("sandbox");
				profile.setSubject("");
				caller.setAPIProfile(profile);
				encoder.add("VERSION", "52.0");
				encoder.add("METHOD","DoExpressCheckoutPayment");

			// Add request-specific fields to the request string.
				// Pass the token value by actual value returned in the SetExpressCheckout.
				encoder.add("TOKEN",token);
				encoder.add("PAYERID",payerID);
				encoder.add("AMT",amount);
				encoder.add("PAYMENTACTION",paymentType);
				encoder.add("CURRENCYCODE",currencyCode);
				encoder.add("SOLUTIONTYPE", "SOLE");
			// Execute the API operation and obtain the response.
				String NVPRequest = encoder.encode();
				String NVPResponse =caller.call(NVPRequest);
				decoder.decode(NVPResponse);
				
			} 
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return decoder.getMap();
		}

}
