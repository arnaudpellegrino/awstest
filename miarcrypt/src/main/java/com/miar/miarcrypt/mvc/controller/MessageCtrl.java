package com.miar.miarcrypt.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.miar.miarcrypt.model.Message;
import com.miar.miarcrypt.service.EncodeSRVC;

@Controller
@RequestMapping("/message")
public class MessageCtrl {

	private Log logger = LogFactory.getLog(this.getClass());
	
	String errorMessage;
	String returnPage;
	
	@RequestMapping(value="/message.page", method = RequestMethod.GET)
    public String viewMessage(@ModelAttribute("messageForm") Message messageForm, ModelMap model) {
		
		logger.debug("Message avec LOGGER debug"); 
		logger.fatal("Message avec LOGGER fatal");
        
        Message message = new Message();
        //message.setMessage("Message to encode");
        //message.setKey("Default Key");
		model.put("messageForm", message);
		        
        return "message";
    }
	
	@RequestMapping(value="/encode.page", method = RequestMethod.POST)
    public String encodeMessage(@ModelAttribute("messageForm") Message messageForm, ModelMap model) {
		
		logger.debug("Message Encoding"); 
		
		String message = messageForm.getMessage();
		String key =  messageForm.getKey();
		int idLoggedUser =  (new Integer(messageForm.getIdUser())).intValue();
		
        
		logger.debug("Message to encode : " + message); 
		logger.debug("Key : " + key); 
		
		
		// Check if parameters have been submitted
        if (message == null)
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage );
        	return "message";
        }else if ("".equalsIgnoreCase(message))
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage);
        	return "message";
        }
        if (key == null)
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage);
        	return "message";
        }else if ("".equalsIgnoreCase(key))
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage);
        	return "message";
        }
		

        // Encode the message
		try {
						
			EncodeSRVC service = new EncodeSRVC(key, idLoggedUser);
			//byte[] messageEncoded = service.encrypt(messageForm.getMessage());
			String codeEncodedMessage = service.encrypt(messageForm.getMessage());
			logger.debug("Code Message Encodé = " + codeEncodedMessage); 
			
			
			model.put("messageCode", codeEncodedMessage );
			model.put("keyToDecode", messageForm.getKey() );
			model.put("result", "Success - Message Encoded !!");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception Constructeur"); 
			e.printStackTrace();
			
		}
		
        return "message";
    }

	
	@RequestMapping(value="/decodeMessage.page", method = RequestMethod.GET)
    public String decodeMessage(@ModelAttribute("messageForm") Message messageForm, ModelMap model) {
		
		logger.debug("Message Decoding"); 
        
        Message message = new Message();
        message.setMessage("Message to decode");
        message.setKey("Default Key");
		model.put("messageForm", message);
		        
        return "messageDecode";
    }
	
	@RequestMapping(value="/decode.page", method = RequestMethod.POST)
    public String doDecodeMessage(@ModelAttribute("messageForm") Message messageForm, ModelMap model) {
		
		logger.debug("Message Decoding"); 
        
		String messageCode = messageForm.getMessage();
		String key =  messageForm.getKey();
		int idLoggedUser =  (new Integer(messageForm.getIdUser())).intValue();
		
		String returnPage = "messageDecode";
        
		logger.debug("Code of Message to decode : " + messageCode); 
		logger.debug("Key : " + key); 
		
		
		// Check if parameters have been submitted
        if (messageCode == null)
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage );
        	return returnPage;
        	
        }else if ("".equalsIgnoreCase(messageCode))
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage);
        	return returnPage;
        }
        if (key == null)
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage);
        	return returnPage;
        }else if ("".equalsIgnoreCase(key))
        {
        	initErrorMessage();
        	model.put("errorMessage", errorMessage);
        	return returnPage;
        }
		
		

		try {
			EncodeSRVC service = new EncodeSRVC(key, idLoggedUser);
			
			//byte[] messageDecoded = service.decrypt(messageForm.getMessage());
			String decodedMessage = service.decrypt(key, messageCode);
			
			logger.debug("Message decode = " + decodedMessage); 
			
			
			model.put("decodedMessage", decodedMessage);
			model.put("keyToDecode",messageForm.getKey() );
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception Constructeur"); 
			e.printStackTrace();
			model.put("errorMessage", "Not possible to decode the message");
			
		}
		
        return "messageDecode";
    }
	
	private void initErrorMessage(){
		
		errorMessage = "You must entre Message and Key";
		
	}
	

	
}
