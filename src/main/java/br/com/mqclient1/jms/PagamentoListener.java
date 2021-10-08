package br.com.mqclient1.jms;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mqclient1.model.PagamentoDTO;


@Service
public class PagamentoListener {

	
    @JmsListener(destination ="FL.SCAM.PAGAMENTO.SITUACAOPAGTO.REQ")
    public void receive(Message message) throws JMSException, JsonMappingException, JsonProcessingException {
    	
    	TextMessage textMessage = (TextMessage) message;
       
		/*
		 *Esse trecho é utilizado pra converter a mensagem que vem da fila, em formato tipado, descomentar na main também o bean
		 * ObjectMapper mapper = new ObjectMapper(); 
		 * String payload = textMessage.getText(); 
		 * PagamentoDTO pagamento = mapper.readValue(payload,PagamentoDTO.class);
		 */
    	
    	
    	//Aqui é implementada a regra de negócio
    	System.out.println(textMessage.getBody(String.class));
		/*
		 * System.out.println(pagamento.getIdentifier());
		 * System.out.println(pagamento.getMessage());
		 */
    }
}
