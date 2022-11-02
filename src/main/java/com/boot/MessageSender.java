package com.boot;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue}") //At runtime this value will be injected
	private String queue;
	
	public void send(String message) {
		System.out.println("Message sent ===> "+message);
//		jmsTemplate.convertAndSend(queue,message); // converts string message to jmsmessage
		MessageCreator mc = s -> s.createTextMessage("Hello Spring JMS!!!");
//		jmsTemplate.setPubSubDomain(false);
		jmsTemplate.send(queue,mc);
	}
}
