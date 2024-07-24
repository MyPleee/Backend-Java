package ple.controllers.usercontrollers.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ple.config.SystemProperties;
import ple.exceptions.customexceptions.UserException;
import ple.exceptions.exceptiontypes.UserErrorType;

public class PasswordHasher {
	
	public String hash(String input) throws UserException{
		
    	String encryptMethod = SystemProperties.getEncryptMethod();
		
        String hashed = "";
        
        if(!encryptMethod.equals("SHA-256")) {
        	throw new UserException(UserErrorType.NotSupportedAlgorithmError);
        }

        try {
            // MessageDigest 객체 생성 
            MessageDigest messageDigest = MessageDigest.getInstance(encryptMethod);

            // 문자열을 바이트 배열로 변환하여 해싱 (32바이트)
            byte[] encodedHash = messageDigest.digest(input.getBytes());

            // 해싱된 바이트 배열을 16진수 문자열로 변환하여 표시
            hashed = this.bytesToHex(encodedHash);

        } catch (NoSuchAlgorithmException e) {
            System.err.println(encryptMethod + " algorithm not available.");
            e.printStackTrace();
        }
        
        System.out.println("input hashed success");
        
        return hashed;
    }

    
	/**
	 * 바이트 배열을 16진수 문자열로 변환하는 메소드
	 * @param hash
	 * @return
	 */
    private String bytesToHex(byte[] hash) {
    	// 16진수 2자리 = 64자리 
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        
        for (byte b : hash) {
        	// 부호 비트가 제거된 순수한 8비트 값
            String hex = Integer.toHexString(0xff & b);
            
            // 1자리인 경우 2자리로 변환
            if (hex.length() == 1) {
                hexString.append('0');
            }
            
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public static void main(String[] args) {
    	PasswordHasher hasher = new PasswordHasher();
    	try {
			System.out.println(hasher.hash("test"));
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
