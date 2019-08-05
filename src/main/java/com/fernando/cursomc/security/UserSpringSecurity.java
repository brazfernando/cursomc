package com.fernando.cursomc.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fernando.cursomc.domain.enums.Perfil;

public class UserSpringSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSpringSecurity() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserSpringSecurity(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		//TODO: começar a aula 68, avançar até o meio, problema com o FLASH
		//this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// Por padrão retornaremos que a conta não irá expirá, caso futuramente haja a necessidade de
		// existir uma regra de negegócio que trata e expiração de sessão, essa deverá ser implementada
		// neste método.
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
