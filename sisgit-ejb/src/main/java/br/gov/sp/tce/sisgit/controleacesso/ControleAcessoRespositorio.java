package br.gov.sp.tce.sisgit.controleacesso;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.gov.sp.tce.sisgit.controleacesso.dao.IUsuarioDAO;
import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;


@Stateless(mappedName = IControleAcessoRespositorio.JNDI_NAME)
public class ControleAcessoRespositorio implements IControleAcessoRespositorio {
	
	@EJB
	private IUsuarioDAO dao;

	@Override
	public List<Usuario> obterUsuarioPorFiltro(Usuario usuarioFiltro) {
		return dao.obterUsuarioPorFiltro(usuarioFiltro);
	}
	
}
