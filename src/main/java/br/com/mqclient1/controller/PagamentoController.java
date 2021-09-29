package br.com.mqclient1.controller;

import com.ibm.mq.jms.MQQueue;

import br.com.mqclient1.model.PagamentoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.nio.charset.StandardCharsets;


@RequestMapping("pagamento")
@RestController
public class PagamentoController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody PagamentoDTO pagamento) throws JMSException {
    
        MQQueue pagamentoQueue = new MQQueue("DEV.QUEUE.1");

        jmsTemplate.convertAndSend(pagamentoQueue, pagamento, textMessage -> {
            textMessage.setJMSCorrelationID(pagamento.getIdentifier());
            return textMessage;
        });

        return new ResponseEntity<Object>(pagamento, HttpStatus.ACCEPTED);
    }



}