package com.mightyjava.utils;

public class ConstantUtils {
	//Here i will define some regexp to validate string and digits
	public static final String CHAR_PATTERN = "[a-zA-Z\\s]+";
	public static final String ID_PATTERN = "[a-zA-Z0-9]+";
	public static final String CODE_PATTERN = "[0-9]{6}";
	public static final String MOBILE_PATTERN = "[0-9]{10}";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static final String END_POINT_URL = "https://s3.us-east-1.amazonaws.com";
	public static final String ACCESS_KEY = "AKIAJGDFBUUJF3LAQUWQ";
	public static final String SECRET_KEY = "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";
	public static final String BUCKET_NAME = "almighty-user-images";
}
