package br.gov.sp.tce.sisgit.infraestrutura.util;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class LogUtil {

	public LogUtil() {   
		//BasicConfigurator.configure(); // then configure
		
		//PropertyConfigurator.configureAndWatch(Configuracao.getInstance().getProperty(Configuracao.LOG4J_PATH));
	}       

}
