package com.miar.miarcrypt.service;

import java.security.Provider;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.miar.miarcrypt.common.util.HibernateUtil;
import com.miar.miarcrypt.domain.Message;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class EncodeSRVC {


		Log logger = LogFactory.getLog(this.getClass());
	
	   private static final String UNICODE_FORMAT = "UTF-8";
	   public static final String ENCRYPTION_SCHEME = "DES";
	   /*
	    * 	AES/CBC/NoPadding (128) 
			AES/CBC/PKCS5Padding (128) 
			AES/ECB/NoPadding (128) 
			AES/ECB/PKCS5Padding (128) 
			DES/CBC/NoPadding (56) 
			DES/CBC/PKCS5Padding (56) 
			DES/ECB/NoPadding (56) 
			DES/ECB/PKCS5Padding (56) 
			DESede/CBC/NoPadding (168) 
			DESede/CBC/PKCS5Padding (168) 
			DESede/ECB/NoPadding (168) 
			DESede/ECB/PKCS5Padding (168) 
			RSA/ECB/PKCS1Padding (1024, 2048) 
			RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048) 
			RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048) 

	    */
	   
	   private KeySpec miarKeySpec;
	   private SecretKeyFactory miarSecretKeyFactory;
	   private Cipher cipher;
	   byte[] miarEncryptionKeyAsBytes;
	   private String miarEncryptionKey;
	   private String miarEncryptionScheme;
	   SecretKey miarSecretKey;
	   Provider p;
	   long idUser;
	   
	   public EncodeSRVC(String pKey, long idLoggedUser) throws Exception
	   {
		   
		   idUser = idLoggedUser;
		   
		   miarEncryptionKey = createKey(pKey);
		   miarEncryptionKeyAsBytes = miarEncryptionKey.getBytes();
		   logger.debug("miarEncryptionKey : " + miarEncryptionKey.toString());
		   logger.debug("miarEncryptionKeyAsBytes : " + miarEncryptionKeyAsBytes.toString());
		   logger.debug("miarEncryptionKeyAsBytes length : " + miarEncryptionKeyAsBytes.length);
	       
	       miarSecretKey = new SecretKeySpec(miarEncryptionKeyAsBytes, ENCRYPTION_SCHEME);
	       cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
	       logger.debug("cipher algo : " + cipher.getAlgorithm());
	       logger.debug("cipher block size : " + cipher.getBlockSize());
	       logger.debug("cipher provider : " + (cipher.getProvider()).getName());
	  	   
		   // TEST	       
/*       
		       //KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			   //SecretKey myDesKey = keygenerator.generateKey();
			   byte[] badEncoded = "Key98123".getBytes() ; //Key data to Test bad key
			   SecretKey badSecretKey = new SecretKeySpec(badEncoded, "DES");	// 	Bad Secret Key for an algorithm
			   
			   byte[] encoded = "Key98765".getBytes() ; 						//	Key data in bytes
			   SecretKey secretKey = new SecretKeySpec(encoded, "DES");			// 	Secret Key for an algorithm
	
			   Cipher desCipher;
	
			   // Create the cipher 
			   //desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			   desCipher = Cipher.getInstance("DES");							// Cipher for the algorithm
			   
			   // Initialize the cipher for encryption
			   //desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
			   desCipher.init(Cipher.ENCRYPT_MODE, secretKey);					// Init cipher to encode with SecretKey
			   
			   //sensitive information
			    byte[] text = "No body can see me me".getBytes();				// Text to encode, in bytes
	
			    logger.debug("Text [Byte Format] : " + text);
			    logger.debug("Text : " + new String(text));
	
			    // Encrypt the text
			    byte[] textEncrypted = desCipher.doFinal(text);					// Encode text, in bytes.
	
			    logger.debug("Text Encryted : " + textEncrypted);
			    logger.debug("Text Encryted Bytes length : " + textEncrypted.length);
			    // Initialize the same cipher for decryption
			    //desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
			    desCipher.init(Cipher.DECRYPT_MODE, secretKey);					// re-init cipher to decode	
			    
			    // Decrypt the text
			    byte[] textDecrypted = desCipher.doFinal(textEncrypted);		// decode the text, in bytes
			    
			    logger.debug("Text Decryted : " + new String(textDecrypted));
			    
			    // Decrypt the text
			    Cipher newCipher = Cipher.getInstance("DES");	
			    newCipher.init(Cipher.DECRYPT_MODE, secretKey);					// re-init cipher to decode	with bad secret key
			    byte[] textNewDecrypted = newCipher.doFinal(textEncrypted);		// decode the text, in bytes
			    
			    logger.debug("Text NEW Decryted : " + new String(textNewDecrypted));
			    
			    // Decrypt the text
			    
			    Cipher badCipher = Cipher.getInstance("DES");	
			    logger.debug("1");
			    badCipher.init(Cipher.DECRYPT_MODE, badSecretKey);					// re-init cipher to decode	with bad secret key
			    logger.debug("2");
			    byte[] textBadDecrypted = badCipher.doFinal(textEncrypted);		// decode the text, in bytes
			    logger.debug("3");
			    
			    logger.debug("Text BAD Decryted : " + new String(textBadDecrypted));
				
*/
		    // END TEST
		    
	       
	   }

	   /**
	    * Method To Encrypt The String, save it to Database and generate a unique CODE to retreive message
	    * later and decode it.
	    */
	   public String encrypt(String stringToEncrypt) {
	   //public byte[] encrypt(String stringToEncrypt) {
	
		   byte[] bytesToEncrypt = null;
		   byte[] encryptedBytes = null;
		   
		   try {
			   
	           cipher.init(Cipher.ENCRYPT_MODE, miarSecretKey);
	           bytesToEncrypt = stringToEncrypt.getBytes();
	           encryptedBytes = cipher.doFinal(bytesToEncrypt);
	           //logger.debug("encryptedBytes : " + encryptedBytes.toString());
	           logger.debug("encryptedBytes length : " + encryptedBytes.length);
	           //logger.debug("encryptedString :" + bytes2String(encryptedBytes));
	           //logger.debug("encryptedString constructeur :" + new String(encryptedBytes));
		       
		       // Generate Message Code
		       Random randomCode = new Random((new Date()).getTime());
		       logger.debug("Random Code next Int = " + randomCode.nextInt());
		       byte[] randowEncoded = cipher.doFinal((randomCode.toString()).getBytes());
		       String messageCode = randowEncoded.toString();
		       logger.debug("messageCode = " + messageCode);
		       
		       // Save to DB
		       Message message = new Message();
		       message.setKey(miarEncryptionKeyAsBytes);
		       message.setMessage(encryptedBytes);
		       message.setCreationTime(new Date() );
		       message.setIdUser(idUser);
		       message.setCode(messageCode);
		       logger.debug("Message ready");
		       
		       		// TODO : manage the DB Part whith DynamoDB
			        /*
		       		//Get Session
			        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			        Session session = sessionFactory.getCurrentSession();
			        //Session session = sessionFactory.openSession();
	
			        //start transaction
			        session.beginTransaction();
			        //Save the Model object
			        session.save(message);
			        //Commit transaction
			        session.getTransaction().commit();
			        logger.debug("Message ID="+message.getId());
			        logger.debug("Message CODE ="+message.getCode());
			       //terminate session factory, otherwise program won't end
			        //sessionFactory.close();
			        //session.close();
			        */
		       
			    return messageCode;
		        		       
	           
	       } catch (Exception e) {
	    	   
	    	   logger.error("ERREUR encrypt");
	           e.printStackTrace();
	       }
	       
	       //return encryptedString;
		   return null;
	   }
	   /**
	    * Method To Decrypt An Ecrypted String
	    */
	   //public String decrypt(byte[] encryptedBytes) {
	   public String decrypt(String _key, String _code ) {
	   //public byte[] decrypt(String encryptedString) {
	       //String decryptedText=null;
		   byte[] bytesToDecrypt=null;
		   //byte[] bytesToDecrypt= encryptedBytes;
		   byte[] bytesDecrypted = null;
	       
	       try {
	    	   
	    	   // Get the encrypted message from DB 
		    	   //Get Session
		           SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		           Session session = sessionFactory.getCurrentSession();
		           //start transaction
		           session.beginTransaction();
		           Query query = session.createQuery("from Message where code = :code ");
		           logger.debug("CODE = " + _code);
		           query.setParameter("code", _code);
		           //Execute the Query
		           List<?> list = query.list();
		           boolean isResultNull = list == null ? true : false;
		           logger.debug("List NULL = "+ isResultNull);
		           logger.debug("List Size = "+ list.size());
		           
		           Message message = (Message)list.get(0);
		           logger.debug("Message ID="+message.getId());
		           //Commit transaction
		           session.getTransaction().commit();
		    	   
		       // Decode the Message           
	           cipher.init(Cipher.DECRYPT_MODE, miarSecretKey);
	           bytesToDecrypt = message.getMessage();
	           
	           bytesDecrypted = cipher.doFinal(bytesToDecrypt);
	          // decryptedText = cipher.doFinal(encryptedText);
	           //decryptedText= bytes2String(plainText);
	           
	           logger.debug("bytesDecrypted : " + bytesDecrypted);
	           logger.debug("bytesDecrypted length : " + bytesDecrypted.length);
	           
	       } catch (Exception e) {
	    	   logger.debug("ERREUR decrypt");
	           e.printStackTrace();
	       }
	       return new String(bytesDecrypted) ;
	   }
	   /**
	    * Returns String From An Array Of Bytes
	    */
	   private static String bytes2String(byte[] bytes) {
	       StringBuffer stringBuffer = new StringBuffer();
	       for (int i = 0; i < bytes.length; i++) {
	           stringBuffer.append((char) bytes[i]);
	       }
	       return stringBuffer.toString();
	   }

	   
	   private String createKey(String pKey){
		   
		   StringBuffer returnKey = new StringBuffer();
		   
		   // check if the parameter is ok
		   String key = pKey==null ? "" : pKey;
		   returnKey.append(key);
		   
		   int keyLenth = returnKey.length();
		   // complete the parameter to have a key of 8 chars long
		   //for (int i = 1; i <= 16 - keyLenth; i++)
		   for (int i = 1; i <= 8 - keyLenth; i++)
		   {
			   returnKey.append(10-i);
		   }
		   
		   logger.debug("ReturnedKey : " + returnKey.toString());
		   
		   return returnKey.toString();

	   }
	   
	   /**
	    * Testing the DES Encryption And Decryption <span id="IL_AD2" class="IL_AD">Technique</span>
	    
	   public static void main(String args []) throws Exception
	   {
	       DESEncryption myEncryptor= new DESEncryption();

	       String stringToEncrypt="Sanjaal.com";
	       String encrypted=myEncryptor.encrypt(stringToEncrypt);
	       String decrypted=myEncryptor.decrypt(encrypted);

	       logger.debug("String To Encrypt: "+stringToEncrypt);
	       logger.debug("Encrypted Value :" + encrypted);
	       logger.debug("Decrypted Value :"+decrypted);

	   }  
	 
	 */

}