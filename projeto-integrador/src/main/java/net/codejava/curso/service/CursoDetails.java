package net.codejava.curso.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.codejava.curso.entities.Curso;

public class CursoDetails implements UserDetails {

	private Curso curso;
	
	public CursoDetails(Curso curso) {
		this.curso = curso;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	public String getCursoDetails() {
		return curso.getCategoria() + " " + curso.getNomecurso();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
