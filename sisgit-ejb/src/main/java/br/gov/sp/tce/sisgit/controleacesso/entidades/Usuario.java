package br.gov.sp.tce.sisgit.controleacesso.entidades;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.gov.sp.tce.sisgit.generico.modelo.base.EntidadeGenerica;



@Entity
@Table(name="usuario")
@NamedQueries({	
	@NamedQuery(name = "usuario.findByEmailAtivoOuInativo", query = "SELECT u FROM Usuario u WHERE lower(u.email) = lower(:email)"),
	@NamedQuery(name = "usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.ativo = true AND lower(u.email) = lower(:email)"),
	@NamedQuery(name = "usuario.findByUniqueId", query = "SELECT u FROM Usuario u WHERE u.ativo = true AND u.idUnico = :idUnico"),	
})
public class Usuario extends EntidadeGenerica implements Cloneable {
	
	@Transient
	private static final long serialVersionUID = 3423619616206574367L;
	
	@Column(name = "id_unico", nullable = false, updatable = false, unique = true)	
	private UUID idUnico;

	@Column(nullable = false, updatable = true, unique = true, length = 100)
	private String email;
	
	@Column(name = "email_secundario", nullable = true, updatable = true, unique = false, length = 100)
	private String emailSecundario;
	
	@Column(nullable = true, updatable = true, unique = true, length = 5)
	private String matricula;
		
	@Column(name = "dt_ultimo_login", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUltimoLogin;
	
	@Column(name = "nome", nullable = true, length = 100)
	private String nome;
	
	// Senha MD5 
	@Column(name = "senha", nullable = true, length = 32)
	private String senha;
			
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = {CascadeType.ALL})
	@Fetch(FetchMode.SELECT)
	private Set<PerfilUsuario> perfils;

	public UUID getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(UUID idUnico) {
		this.idUnico = idUnico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailSecundario() {
		return emailSecundario;
	}

	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDtUltimoLogin() {
		return dtUltimoLogin;
	}

	public void setDtUltimoLogin(Date dtUltimoLogin) {
		this.dtUltimoLogin = dtUltimoLogin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<PerfilUsuario> getPerfils() {
		return perfils;
	}

	public void setPerfils(Set<PerfilUsuario> perfils) {
		this.perfils = perfils;
	}	
}
