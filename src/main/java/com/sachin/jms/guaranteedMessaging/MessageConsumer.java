package com.sachin.jms.guaranteedMessaging;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageConsumer {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		/*
		 * Messaging mode is set while creating JMSContext. Example of using
		 * CLIENT_ACKNOWLEDGE mode on JMS producer side. 
		 */
		//JMSContext jmsContext = connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
		
		/*
		 * Uncomment above code and comment below one line to see CLIENT_ACKNOWLEDGE mode
		 */
		JMSContext jmsContext = connectionFactory.createContext();

		JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
		TextMessage textMessage = (TextMessage) consumer.receive();
		System.out.println(textMessage.getText());
		/*
		 * When the below line is commented, the message can be consumed any number of
		 * times as the JMS server will keep the message becaus it has not receive the
		 * acknowledgment from the consumer yet. When we send the acknowledgment using 
		 * below method call, JMS server will remove the message
		 */
		//textMessage.acknowledge();

	}

}
