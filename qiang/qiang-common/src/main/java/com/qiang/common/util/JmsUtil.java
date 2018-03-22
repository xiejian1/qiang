package com.qiang.common.util;



import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.*;
import java.io.Serializable;


/**
 * Created by xieqiang_daye on 2018/2/27.
 */
public class JmsUtil {

    /**
     * 发送文本消息
     * @param destination
     * @param jmsTemplate
     * @param textMessage
     * */
    public static void sendMessage(JmsTemplate jmsTemplate, Destination destination, final  String textMessage){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return  session.createTextMessage(textMessage);
            }
        });
    }
    /**
     * 发送对象消息
     * */
    public static  void sendMessage(JmsTemplate jmsTemplate, Destination destination, final Serializable objectMessage){
       jmsTemplate.send(destination, new MessageCreator() {
           @Override
           public Message createMessage(Session session) throws JMSException {
               return session.createObjectMessage(objectMessage);
           }
       });
    }
    /**
     * 延迟发送消息队列
     * */
    public  static  void sendMessageDelay(JmsTemplate jmsTemplate,Destination destination,final Serializable objectMessage,final Long delay){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage om = session.createObjectMessage(objectMessage);
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,1*1000);
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,1);
                return om;
            }
        });
    }

}
