package br.gov.sp.tce.sisgit.infraestrutura.jms.servico;



import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;


@Stateless(mappedName=IProdutorDeMensagem.JNDI)
public class ProdutorDeMensagem implements IProdutorDeMensagem{
	
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
 
   // @Resource(mappedName = "java:/queue/test")
    private Destination destination;
    
   // @Resource(mappedName = "java:/topic/test")
	private Topic topic;
 
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;
 
    @PostConstruct
    public void init() {
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
 
    @PreDestroy
    public void destroy() {
        if (connection != null) {
            try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
 
    public void enviarMensagem(String texto) {
        ObjectMessage message;
        try {
            message = session.createObjectMessage(texto);
            messageProducer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void enviarParaFilaTopic(String texto) {
				
		try {
			Connection conexao = (Connection) connectionFactory.createConnection();
			Session sessao = ((javax.jms.Connection) conexao).createSession(false,Session.AUTO_ACKNOWLEDGE);
			MessageProducer produtor = sessao.createProducer(topic);
			ObjectMessage msg = sessao.createObjectMessage();
			msg.setObject(texto);
			produtor.send(msg);
			conexao.close();	
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}
 
}