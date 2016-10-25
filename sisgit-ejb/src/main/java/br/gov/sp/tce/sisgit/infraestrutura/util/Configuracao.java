package br.gov.sp.tce.sisgit.infraestrutura.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;



public class Configuracao {

	public static final String LOG4J_PATH = "log4jpath";
	
	public static final String APLICACAO_PATH = "aplicacaopath";
	
	public static final String URL_FILA = "url_fila";
	public static final String NOME_FILA_JMS = "nomefilaJms";
	public static final String NOME_FABRICA_CONEXAO = "nomeFabricaConexao";
	public static final String LOGIN_USUARIO_DA_FILA = "loginUsuarioDaFila";
	public static final String SENHA_DO_USUARIO_DA_FILA = "senhaDoUsuarioDaFila";
	
	private static Configuracao config = null;
	private Properties properties;
	
	private Configuracao(Properties properties) {
		this.properties = properties;
	}

	public static Configuracao getInstance() {
		if (config == null) {
			try {
				Properties prop = new Properties();
				prop.load(Configuracao.class
						.getResourceAsStream("/config.properties"));

				Properties propertiesAplicacao = new Properties();
				
				// pegar do BD
				
				FileInputStream fileInputStream = new FileInputStream(prop.getProperty(APLICACAO_PATH));
				
				propertiesAplicacao.load(fileInputStream);
				
				Set<Entry<Object, Object>> entrySet = propertiesAplicacao.entrySet();
				
				if(entrySet != null && !entrySet.isEmpty()){					
					for (Entry<Object, Object> entry : entrySet) {
						prop.put(entry.getKey(), entry.getValue());
					}
				}
				
				config = new Configuracao(prop);
				
				
			} catch (IOException e) {
				
			}
		}

		return config;
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	public String getProperty(String property, String defaultValue) {
		return properties.getProperty(property, defaultValue);
	}
	
}
