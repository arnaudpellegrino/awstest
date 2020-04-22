package com.miar.miarcrypt;

import org.junit.Test;

import com.miar.miarcrypt.service.EncodeSRVC;

public class EncodeSrvcTest {
	
	@Test
	public void encodeTest(){
		
		// pkey et idLogger
		try {
		
			EncodeSRVC encodeService = new EncodeSRVC("pkeyTest", 123);
			String encryptedMess = encodeService.encrypt("Test Message");
			
			System.out.println("CRYPTED MESSAGE  : " + encryptedMess);
			
		}catch(Exception e){
			
			System.out.println("Error Encrypting : " + e.getMessage());
			
		}
		
		
	}
	

	@Test
	public void encodeAutreTest(){
		
		// pkey et idLogger
		try {
		
			EncodeSRVC encodeService = new EncodeSRVC("pkeyTest", 123);
			String encryptedMess = encodeService.encrypt("Autre Test Message");
			
			System.out.println("CRYPTED MESSAGE  : " + encryptedMess);
			
		}catch(Exception e){
			
			System.out.println("Error Encrypting : " + e.getMessage());
			
		}
		
		
	}
	
}
