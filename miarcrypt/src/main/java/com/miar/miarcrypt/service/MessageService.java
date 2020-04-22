package com.miar.miarcrypt.service;

import com.miar.miarcrypt.dto.Message;
import com.miar.miarcrypt.dto.Person;

public interface MessageService {
	
	public Person hello();
	public String postcrypt(final Message message);
	
}
