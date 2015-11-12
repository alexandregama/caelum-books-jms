package br.com.caelumbooks.queue.consumer;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegistraGeradorDeNotaFiscalDaFila {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue fila = (Queue) ic.lookup("jms/FILA.NOTA-FISCAL");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(fila);
			consumer.setMessageListener(new TratadorDeMensagemDaFilaParaGerarNotaFiscal());
			
			context.start();
			System.out.println("Iniciado Tratador de mensagens para gerar nota fiscal por fila...");
			
			Scanner teclado = new Scanner(System.in);
			teclado.nextLine();
			
			context.stop();
			teclado.close();
			System.out.println("Tratador finalizado!");
		}
	}
	
}
