package br.com.caelumbooks.topic.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviadorFinanceiroParaOTopico {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topico = (Topic) ic.lookup("jms/TOPICO.LIVRARIA");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			context.createProducer().send(topico, "Msg enviada para o Financeiro");
		}
	}
	
}
