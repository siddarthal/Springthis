package com.project.phoneBook.exception;

import java.util.HashMap;

public class PhoneBookException extends RuntimeException {
	private String errorMessage;
   public PhoneBookException(String msg) {
	   super(msg);
	   this.errorMessage=msg;
   }
   public HashMap<String,String> getErrors(String status){
	   HashMap<String,String> hm= new HashMap<>();
	   hm.put("message",errorMessage);
	   hm.put("status", status);
	   return hm;
   }
   
}
