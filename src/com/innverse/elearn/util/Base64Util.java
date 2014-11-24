package com.innverse.elearn.util;

import org.springframework.security.crypto.codec.Base64;

public class Base64Util {

	public static void main(String[] args) throws Exception {
		String cd = "courseId=1&memberId=1&organizationId=1";

		// encrypt data on your side using BASE64
		byte[]   bytesEncoded = Base64.encode(cd.getBytes());
		String bes = new String(bytesEncoded );
		System.out.println("ecncoded value is " + new String(bytesEncoded ));

		// Decrypt data on other side, by processing encoded data
		byte[] valueDecoded= Base64.decode(bes.getBytes() );
		System.out.println("Decoded value is " + new String(valueDecoded));
		
	
	}
	
	public static String encode(String base){
		byte[]   bytesEncoded = Base64.encode(base.getBytes());
		String bes = new String(bytesEncoded );
		return bes;
	}
	
	public static String decode(String base){
		// Decrypt data on other side, by processing encoded data
		byte[] valueDecoded= Base64.decode(base.getBytes() );
		String bds = new String(valueDecoded);
		System.out.println("Decoded value is " + bds);
		return bds;
		
	}
}
