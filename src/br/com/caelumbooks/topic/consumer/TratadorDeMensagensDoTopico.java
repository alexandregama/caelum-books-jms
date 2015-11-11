package br.com.caelumbooks.topic.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TratadorDeMensagensDoTopico implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("Mensagem recebida do Topico: " + msg.getText());
		} catch(JMSException e) {
			e.printStackTrace();
		}
	}
}
