package br.gov.sp.tce.sisgit.generico.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe para armazenar parâmetros utilizados em operações de acesso a banco (JPA/jdbcTemplate)
 * 
 * Os campos são public final, o construtor é privado, e a instanciação só pode ser feita através do método of(key,value)
 * 
 * @author dvivencio
 *
 */
public class Param implements Serializable {

	private static final long serialVersionUID = 1047064777921046311L;
	
	public final String chave;
	public final Serializable valor;
	
	private <T extends Serializable> Param(final String chave, final T valor) {
		if (chave == null)
			throw new IllegalArgumentException("Chave não deve ser null");
		this.chave = chave;
		this.valor = valor;
	}

	public static <T extends Serializable> Param of (final String key, final T value){
		return new Param(key, value);
	}
	
	public static <T extends Serializable> Param of (final String key, final Collection<T> values){
		return new Param(key, new ArrayList<>(values));
	}
		
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Param p1 = Param.of("A", 1L);
		Param p2 = Param.of("B", "dchjhjsdsj");
		Param[] p3 = null;
//		Param.list(p3);
	}
}
