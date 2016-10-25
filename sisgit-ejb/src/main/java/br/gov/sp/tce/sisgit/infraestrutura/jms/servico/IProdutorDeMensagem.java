package br.gov.sp.tce.sisgit.infraestrutura.jms.servico;

public interface IProdutorDeMensagem {
	
	public String JNDI = "java:module/ProdutorDeMensagem";

	void enviarMensagem(String texto);

	void enviarParaFilaTopic(String texto);

}
