package com.baccarin.gerenciador_usuarios.repository;

import com.baccarin.gerenciador_usuarios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByDocumentoId(Long documentoId);

}
