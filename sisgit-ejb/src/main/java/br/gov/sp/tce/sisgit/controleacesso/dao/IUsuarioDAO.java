package br.gov.sp.tce.sisgit.controleacesso.dao;

import java.util.List;

import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;
import br.gov.sp.tce.sisgit.generico.dao.base.DAOAbstrato;

public interface IUsuarioDAO  extends DAOAbstrato<Usuario>  {

	List<Usuario> obterUsuarioPorFiltro(Usuario usuarioFiltro);

}
