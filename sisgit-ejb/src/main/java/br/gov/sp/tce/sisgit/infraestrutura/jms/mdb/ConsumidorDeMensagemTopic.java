package br.gov.sp.tce.sisgit.infraestrutura.jms.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

/*@MessageDriven(name = "testTopic", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })*/
public class ConsumidorDeMensagemTopic implements MessageListener {

	Logger logger = Logger.getLogger(ConsumidorDeMensagemTopic.class);
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;

		try {
			logger.info("No backend, recv text=" + objectMessage.getObject());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}