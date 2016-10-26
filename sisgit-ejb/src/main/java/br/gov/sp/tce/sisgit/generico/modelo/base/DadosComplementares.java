package br.gov.sp.tce.sisgit.generico.modelo.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.sp.tce.sisgit.controleacesso.entidades.Usuario;
import br.gov.sp.tce.sisgit.infraestrutura.annotation.Auditable;

/**
 * 
 * @author dvivencio
 *
 */
@MappedSuperclass
@Auditable(fields = {"alteradoEm", "alteradoPor", "ativo"})
public abstract class DadosComplementares {
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;

	@Column(name="criado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alterador_por")
	private Usuario alteradoPor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="criado_por")
	private Usuario criadoPor;
	
	@Column(name="ativo")
	private Boolean ativo;
	
	public Date getAlteradoEm() {
		return this.alteradoEm;
	}

	public void setAlteradoEm(Date alteradoEm) {
		this.alteradoEm = alteradoEm;
	}

	public Date getCriadoEm() {
		return this.criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	public Usuario getAlteradoPor() {
		return this.alteradoPor;
	}

	public void setAlteradoPor(Usuario alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	public Usuario getCriadoPor() {
		return this.criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}
	
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(final Boolean ativo) {
		this.ativo = ativo;
	}

}