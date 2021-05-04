package com.BeyondLearning.projetointegrador.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cursos")

public class Cursos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cursos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCursos() {
		return cursos;
	}

	public void setCursos(String cursos) {
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		return "Cursos [id=" + id + ", cursos=" + cursos + "]";
	}

}
