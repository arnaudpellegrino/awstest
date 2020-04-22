package com.miar.miarcrypt.business;

import org.springframework.stereotype.Service;

import com.miar.miarcrypt.dto.Message;

@Service
public class CryptBean implements Crypt {

	@Override
	public String crypt(Message message) {
		
		
		
		
		String encryptedMess =  "The message is : " + message.getTextToCrypt() + " - Key : " + message.getKey();
		
		
		
		return encryptedMess;
	}

	@Override
	public String uncrypt(Message message) {
		return null;
	}

}
