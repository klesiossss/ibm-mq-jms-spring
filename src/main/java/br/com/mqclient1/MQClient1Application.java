package br.com.mqclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


@EnableJms
@SpringBootApplication
public class MQClient1Application {

	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack" , "true");
		SpringApplication.run(MQClient1Application.class, args);
		
		}
	
	
		/*
		 * // Serialize message content to json using TextMessage
		 * 
		 * @Bean public MessageConverter jacksonJmsMessageConverter() {
		 * System.setProperty("java.net.preferIPv4Stack" , "true");
		 * MappingJackson2MessageConverter converter = new
		 * MappingJackson2MessageConverter(); converter.setTargetType(MessageType.TEXT);
		 * converter.setTypeIdPropertyName("_type"); return converter; }
		 */

}
