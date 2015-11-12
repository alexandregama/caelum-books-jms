package br.com.caelumbooks.queue.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviadorParaAFilaDeNotaFiscal {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue fila = (Queue) ic.lookup("jms/FILA.NOTA-FISCAL");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			context.createProducer().send(fila, "Msg para gerar nota fiscal");
			System.out.println("Mensagem enviada!");
		}
	}
	
}
