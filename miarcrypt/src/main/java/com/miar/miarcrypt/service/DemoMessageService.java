package com.miar.miarcrypt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miar.miarcrypt.business.CryptBean;
import com.miar.miarcrypt.dto.Message;
import com.miar.miarcrypt.dto.Person;

// final
@Service
public class DemoMessageService implements MessageService {

	@Autowired
	CryptBean crytpbean;
	
	public DemoMessageService() {
		super();
	}

	@Override
	public Person hello() {
	    final Person person = new Person();
	    person.setFirstname("Bernardo");
	    person.setLastname("SMITH");
	    return person;
	}

	@Override
	public String postcrypt(Message message) {
		return crytpbean.crypt(message);
	}

	
	
}
