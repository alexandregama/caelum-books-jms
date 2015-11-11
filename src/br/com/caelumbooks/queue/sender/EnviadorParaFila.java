package br.com.caelumbooks.queue.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviadorParaFila {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			context.createProducer().send(queue, "Minha mensagem para a Queue");
		}
		
	}
	
}
