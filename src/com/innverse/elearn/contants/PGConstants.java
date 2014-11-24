package com.innverse.elearn.contants;

public class PGConstants {

	public static String host = "/ELMS/org";
	
	public static String ppPaymentGateway = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=";
	public static String returnURL = "/paypal/pg/success";
	public static String cancelURL = "/paypal/pg/cancel";

	public static String paymentType = "Sale";
	public static String currencyCode = "USD";
}
