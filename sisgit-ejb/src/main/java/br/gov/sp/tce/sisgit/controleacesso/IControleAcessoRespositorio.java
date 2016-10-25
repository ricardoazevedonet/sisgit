package br.gov.sp.tce.sisgit.controleacesso;

import java.util.List;

import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;

public interface IControleAcessoRespositorio {
	
	String JNDI_NAME =  "java:module/ControleAcessoRespositorio";
	
	
	List<Usuario> obterUsuarioPorFiltro(Usuario usuarioFiltro);

}
