package com.miar.miarcrypt.business;

import com.miar.miarcrypt.dto.Message;

public interface Crypt {

	public String crypt(Message message);
	public String uncrypt(Message message);
	
}
