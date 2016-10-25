package br.gov.sp.tce.sisgit.controleacesso.ws;

import javax.ejb.Stateless;
import javax.jws.WebService;


@WebService
@Stateless
public class ControleAcessoWS implements IControleAcessoWS{

	@Override
	public String heloWord() {
		
		return "heloword";
	}

	
	
}
