package tacos.jms;


import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import tacos.domain.Order;

import javax.jms.JMSException;
import javax.jms.Message;

public class JmsOrderReceiver {
    private JmsTemplate jmsTemplate;
    private MessageConverter messageConverter;

    public JmsOrderReceiver(JmsTemplate jmsTemplate, MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    public Order receiveOrderold() throws JMSException {
        Message receive = jmsTemplate.receive("tacocloud.order.queue");
        return ((Order) messageConverter.fromMessage(receive));
    }

    public Order receiveOrder() throws JMSException {
        return ((Order) jmsTemplate.receiveAndConvert("tacocloud.order.queue"));
    }
}
