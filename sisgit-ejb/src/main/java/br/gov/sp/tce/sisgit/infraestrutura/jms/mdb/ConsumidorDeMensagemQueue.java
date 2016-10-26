package br.gov.sp.tce.sisgit.infraestrutura.jms.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/*@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test") })*/
public class ConsumidorDeMensagemQueue implements MessageListener {


	@Override
	public void onMessage(javax.jms.Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;

		try {
			System.out.println("No backend, recv text=" + objectMessage.getObject());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
