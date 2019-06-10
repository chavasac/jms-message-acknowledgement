package com.sachin.jms.guaranteedMessaging;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageProducer {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		/*
		 * Messaging mode is set while creating JMSContext. Example of using
		 * AUTO_ACKNOWLEDGE mode on JMS producer side
		 */
		//JMSContext jmsContext = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);

		/*
		 * Example of using DUPS_OK_ACKNOWLEDGE mode on JMS producer side
		 */
		JMSContext jmsContext = connectionFactory.createContext(JMSContext.DUPS_OK_ACKNOWLEDGE);

		JMSProducer producer = jmsContext.createProducer();
		producer.send(requestQueue, "Message 1");

	}

}
