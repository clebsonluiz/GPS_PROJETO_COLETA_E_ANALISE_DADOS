package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface SecurityUtil {

	
	
	public static String criptografarMD5(String original) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
		
		StringBuilder hexString = new StringBuilder();
		
		for (byte b : messageDigest)
		  hexString.append(String.format("%02X", 0xFF & b));
		
		algorithm.reset();
		return hexString.toString();
	}
	
	public static String criptografarSHA2(String original) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		
		for (byte b : messageDigest) 
		  hexString.append(String.format("%02X", 0xFF & b));
		
		algorithm.reset();
		return hexString.toString();
	}
	
}
