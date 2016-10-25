package br.gov.sp.tce.sisgit.controleacesso;

import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;

public interface IGerenciadorControleAcesso {
	
	String JNDI_NAME =  "java:module/GerenciadorControleAcesso";
	
	void salvarUsuario(Usuario usuario);

}
