package org.bootcamp.mycash.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.bootcamp.mycash.domain.Usuario;
import org.bootcamp.mycash.domain.UsuarioRole;
import org.bootcamp.mycash.exception.UsuarioException;
import org.bootcamp.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public List<Usuario> todos() {
		return repo.findAll();
	}
	
	public void registraUsuarioAdmin(String email, String senha) {
		if (repo.findByEmail(email).isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setRole(UsuarioRole.ROLE_ADMIN);
			
			repo.save(usuario);
		}
	}

	public Usuario save(String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setRole(UsuarioRole.ROLE_USER);
		
		if (repo.findByEmail(email).isPresent()) {
			throw new UsuarioException("Ja existe usuario com este e-mail");
		}
		return repo.save(usuario);
	}

	public Usuario findByEmail(String email) {
		return repo.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Usuario resetarSenha(String email, String senhaNova) {
		Usuario usuario = findByEmail(email);
		usuario.setSenha(senhaNova);
		return repo.save(usuario);
	}
	
}
