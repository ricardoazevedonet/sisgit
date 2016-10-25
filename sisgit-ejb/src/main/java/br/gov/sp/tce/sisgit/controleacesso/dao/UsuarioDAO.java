package br.gov.sp.tce.sisgit.controleacesso.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;
import br.gov.sp.tce.sisgit.generico.dao.base.DAOAbstratoImpl;

@Stateless
public class UsuarioDAO extends DAOAbstratoImpl<Usuario> implements IUsuarioDAO {

	@Override
	public List<Usuario> obterUsuarioPorFiltro(Usuario usuarioFiltro) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
