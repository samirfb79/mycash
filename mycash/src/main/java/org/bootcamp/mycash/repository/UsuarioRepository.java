package org.bootcamp.mycash.repository;

import java.util.Optional;

import org.bootcamp.mycash.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByEmail(String email);

}
