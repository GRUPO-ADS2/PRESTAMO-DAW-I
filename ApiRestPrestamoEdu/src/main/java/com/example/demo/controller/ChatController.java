package com.example.demo.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.models.documents.Message;

@Controller
public class ChatController {

	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Message recibeMessage(Message sms) {
		sms.setFecha(new Date().getTime());
		sms.setBody("recibido por el broker :" +sms.getBody());
		
		return sms;
	}
}
