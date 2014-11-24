package com.innverse.elearn.paypal.express.checkout;

import java.util.Map;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;

public class ECGetExpressCheckout {

	private NVPCallerServices caller = null;
	public static void main(String[] args){

		//the parameters for the service
		ECGetExpressCheckout checkout = new ECGetExpressCheckout();
		
		try {
			//calling the service, setting up the checkoutpage
			String token = checkout.ECGetExpressCheckoutCode("EC-5R72376006372604P");
			 System.out.println("Url to redirect to: https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=" + token);
		} catch (Exception e) {
			
		}
	}
	
	public String ECGetExpressCheckoutCode(String token)
	{

		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		
		try
		{
		caller = new NVPCallerServices();
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		
		 /*WARNING: Do not embed plaintext credentials in your application code.
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
		encoder.add("METHOD", "GetExpressCheckoutDetails");

		// Add request-specific fields to the request string.
		// Pass the token value returned in SetExpressCheckout.
		encoder.add("TOKEN", token);

		// Execute the API operation and obtain the response.
		String NVPRequest = encoder.encode();
		String NVPResponse = caller.call(NVPRequest);
		decoder.decode(NVPResponse);
			
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return decoder.get("ACK");
	}

	public Map mECGetExpressCheckout(String token)
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
		encoder.add("METHOD", "GetExpressCheckoutDetails");

		// Add request-specific fields to the request string.
		// Pass the token value returned in SetExpressCheckout.
		encoder.add("TOKEN", token);

		// Execute the API operation and obtain the response.
		String NVPRequest = encoder.encode();
		String NVPResponse = caller.call(NVPRequest);
		decoder.decode(NVPResponse);
			
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return decoder.getMap();
	}

}
