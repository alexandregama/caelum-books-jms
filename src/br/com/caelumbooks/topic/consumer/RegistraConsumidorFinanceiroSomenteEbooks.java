package br.com.caelumbooks.topic.consumer;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegistraConsumidorFinanceiroSomenteEbooks {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topico = (Topic) ic.lookup("jms/TOPICO.LIVRARIA");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			context.setClientID("FinanceiroEbook");
			JMSConsumer consumer = context.createDurableConsumer(topico, "AssinaturaNotasEbook", "formato='ebook'", false);
			consumer.setMessageListener(new TratadorDeMensagemFinanceiroParaEbook());
			
			context.start();
			
			Scanner teclado = new Scanner(System.in);
			System.out.println("Tratador de mensagens Financeiro para Ebooks esperando msgs...");
			
			teclado.nextLine();
			
			System.out.println("Tratador finalizado!");
			teclado.close();
			context.stop();
		}
	}
	
}
