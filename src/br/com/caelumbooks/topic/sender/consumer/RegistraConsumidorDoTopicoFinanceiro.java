package br.com.caelumbooks.topic.sender.consumer;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelumbooks.topic.consumer.TratadorDeMensagensDoTopico;

public class RegistraConsumidorDoTopicoFinanceiro {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topico = (Topic) ic.lookup("jms/TOPICO.LIVRARIA");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			context.setClientID("Financeiro");
			JMSConsumer consumer = context.createDurableConsumer(topico, "AssinaturaNotas");
			consumer.setMessageListener(new TratadorDeMensagensDoTopico());
			
			context.start();
			
			Scanner teclado = new Scanner(System.in);
			System.out.println("Tratador esperando as mensagens do Topico...");
			teclado.nextLine();
			
			teclado.close();
			context.stop();
			System.out.println("Tratador finalizado!");
		}
	}
	
}
