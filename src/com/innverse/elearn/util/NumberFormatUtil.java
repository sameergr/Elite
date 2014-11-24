package com.innverse.elearn.util;

import java.text.DecimalFormat;

public class NumberFormatUtil {

	private static DecimalFormat df = new DecimalFormat("###.##");
	
	public static final double twoDecimalDigits(double value){
		return Double.valueOf( df.format(value) );
	}
}
