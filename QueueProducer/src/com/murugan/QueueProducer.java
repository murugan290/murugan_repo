package com.murugan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueProducer {

	public static void main(String[] args) throws NamingException, JMSException, IOException {
		System.out.println("TESTING after installing GIT");
		System.out.println("second test");
		System.out.println("third test");
		System.out.println("Starting QueueProducer to send message");
		Context initialContext = QueueProducer.getInitialContext();
		QueueConnectionFactory conFactory = (QueueConnectionFactory) initialContext.lookup("java:comp/DefaultJMSConnectionFactory");
		Queue queue = (Queue) initialContext.lookup("queue/myQueue");
		QueueConnection queueCon = conFactory.createQueueConnection();
		QueueSession session = (QueueSession) queueCon.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		queueCon.start();
		QueueProducer producer = new QueueProducer();
		String defaultMsg = "First JMS testing!!!!";
		String msg = args[0]!=null?args[0]:defaultMsg;
		producer.sendMessage(msg, session,queue);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("You want to send another message?");
		String s = reader.readLine();
		while (true) {
			if (s.equalsIgnoreCase("exit")) {
				queueCon.close();
				System.out.println("End of QueueProducer. Message sent to queue and has to be consumed by receiver!!!!!");
				System.exit(0);// exit program
			}else{
				producer.sendMessage(msg, session,queue);
				System.out.println("You want to send another message?");
				s = reader.readLine();
			}
		}
		
	}
	
	public void sendMessage(String msg,QueueSession session,Queue queue) throws JMSException{
		System.out.println("ready to send message!!!!!" + msg);
		QueueSender sender = session.createSender(queue);
		TextMessage message = session.createTextMessage(msg);
		sender.send(message);
		sender.close();
		}
		
	
	public static Context getInitialContext() throws NamingException{
		Properties prop = new Properties();
		prop.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		prop.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
		prop.setProperty("java.naming.provider.url", "iiop://localhost:3700");
		return new InitialContext(prop);
		}

}
