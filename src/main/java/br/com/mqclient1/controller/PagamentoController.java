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
    //    log.info("### 1 ### Order Service sending order message '{}' to the queue", order.getMessage());

    	
        MQQueue orderRequestQueue = new MQQueue("DEV.QUEUE.1");

        jmsTemplate.convertAndSend(orderRequestQueue, pagamento, textMessage -> {
            textMessage.setJMSCorrelationID(pagamento.getIdentifier());
            return textMessage;
        });

        return new ResponseEntity<Object>(pagamento, HttpStatus.ACCEPTED);
    }


    @Deprecated // this was just to show how to find a message by correlation Id
    @GetMapping
    public ResponseEntity<PagamentoDTO> findOrderByCorrelationId(@RequestParam String correlationId) throws JMSException {
  //      log.info("Looking for message '{}'", correlationId);
        String convertedId = bytesToHex(correlationId.getBytes());
        final String selectorExpression = String.format("JMSCorrelationID='ID:%s'", convertedId);
        final TextMessage responseMessage = (TextMessage) jmsTemplate.receiveSelected("DEV.QUEUE.1", selectorExpression);
        PagamentoDTO response = new  PagamentoDTO();
                response.setMessage(responseMessage.getText());
                response.setIdentifier(correlationId);
                
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // You could use Apache Commons Codec library instead
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes();
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}