package br.gov.sp.tce.sisgit.generico.exception;

/**
 * RuntimeException para erros envolvendo usuários que se autenticam com sucesso diante do LDAP, mas
 * não possuem cadastro no sistema
 * 
 * @author p-lgaona
 * @version 1.0
 * @since 1.0
 * 
 */
public class SemCadastroException extends RuntimeException {

	private static final long serialVersionUID = 5170189547807846824L;

	public SemCadastroException() {
		super("Login não encontrado na base de usuários do sistema. Por favor, entre em contato com um administrador");
	}

}