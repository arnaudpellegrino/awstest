package com.miar.miarcrypt.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miar.miarcrypt.dto.Message;
import com.miar.miarcrypt.dto.Person;
import com.miar.miarcrypt.service.MessageService;

@RestController
@RequestMapping("/miar")
class MessageController {

	//@NonNull
	//private MessageService messageService = new DemoMessageService(); //final  // = new DemoMessageService();
	
	@Autowired
	MessageService messageService;
	
	
//	@GetMapping("/hello")
//	public Person hello() {
//		
//		return messageService.hello();
//		
//	}

  //@RequestBody
  @PostMapping("/crypt")
  public String postcrypt( final Message message) {
	  
	  return messageService.postcrypt(message);
	  
  }

  
  @PostMapping("/uncrypt")
  public String postUncrypt(@RequestBody final Message message) {
    return "Message to uncrypt : " + message.getCrypted() + " !";
  }
  
}
