package com.BeyondLearning.projetointegrador.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int cpf;
	private String email;
	private int nivel;
	private String telefone;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getCpf() {
		return cpf;
	}



	public void setCpf(int cpf) {
		this.cpf = cpf;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getNivel() {
		return nivel;
	}



	public void setNivel(int nivel) {
		this.nivel = nivel;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public Cursos getCursos() {
		return cursos;
	}



	public void setCursos(Cursos cursos) {
		this.cursos = cursos;
	}



	@ManyToOne
	@JoinColumn(name="id_cursos")
	private Cursos cursos;



	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", nivel=" + nivel
				+ ", telefone=" + telefone + ", cursos=" + cursos + "]";
	}
	
	
	
}
