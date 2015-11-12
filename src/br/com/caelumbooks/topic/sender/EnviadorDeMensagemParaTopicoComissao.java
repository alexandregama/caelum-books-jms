package br.com.caelumbooks.topic.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviadorDeMensagemParaTopicoComissao {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topico = (Topic) ic.lookup("jms/TOPIC.COMISSAO");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			producer.send(topico, "Msg para comissao");
			System.out.println("Msg enviada!");
		}
	}
	
}
