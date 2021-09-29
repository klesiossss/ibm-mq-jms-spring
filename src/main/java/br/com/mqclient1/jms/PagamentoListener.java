package br.com.mqclient1.jms;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mqclient1.model.PagamentoDTO;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;


@Component
public class PagamentoListener {

    @JmsListener(destination = "DEV.QUEUE.1")
    public void receive(Message message) throws JMSException, JsonMappingException, JsonProcessingException {
        TextMessage textMessage = (TextMessage) message;

       ObjectMapper mapper = new ObjectMapper();
       
        String payload = textMessage.getText();
        
       PagamentoDTO pagamento = mapper.readValue(payload, PagamentoDTO.class);
        
        System.out.println(pagamento.getIdentifier());
        System.out.println(pagamento.getMessage());
   
    }
}
