package br.com.caelumbooks.topic.consumer;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegistraSubscriberDeTopicoParaGerarComissao {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topico = (Topic) ic.lookup("jms/TOPIC.COMISSAO");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(topico); //Por padrao o consumidor de um topico não é duravel
			consumer.setMessageListener(new TratadorDeMensagemDeTopicoParaComissao());
			
			context.start();
			System.out.println("Tratador de mensagens para comissao iniciado...");
			
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();
			
			context.stop();
			teclado.close();
			System.out.println("Tratador finalizado!");
		}
	}
	
}
