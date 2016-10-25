package br.gov.sp.tce.sisgit.controleacesso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;

@Stateless(mappedName = IGerenciadorControleAcesso.JNDI_NAME)
public class GerenciadorControleAcesso implements IGerenciadorControleAcesso {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void salvarUsuario(Usuario usuario) {	
		em.merge(usuario);
	}

}
