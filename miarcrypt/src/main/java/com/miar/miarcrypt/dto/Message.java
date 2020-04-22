package com.miar.miarcrypt.dto;

import lombok.Data;

@Data
public class Message {
  String textToCrypt, key, crypted; 
}