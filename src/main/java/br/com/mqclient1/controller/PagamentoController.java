package br.com.mqclient1.controller;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mq.jms.MQQueue;

import br.com.mqclient1.model.PagamentoDTO;


@RequestMapping("/pagamento")
@RestController
public class PagamentoController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping										  //PagamentoDTO
    public ResponseEntity<Object> createOrder(@RequestBody  String pagamento) throws JMSException {
  
        MQQueue pagamentoQueue = new MQQueue("FL.SCAM.PAGAMENTO.SITUACAOPAGTO.REQ");
        		

        jmsTemplate.convertAndSend(pagamentoQueue, pagamento, textMessage -> {
            //textMessage.setJMSCorrelationID(pagamento.getIdentifier());
            return textMessage;
        });

        return new ResponseEntity<Object>(pagamento, HttpStatus.ACCEPTED);
    }



}