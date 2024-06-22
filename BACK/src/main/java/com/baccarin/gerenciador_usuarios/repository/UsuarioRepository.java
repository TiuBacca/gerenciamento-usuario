package com.baccarin.gerenciador_usuarios.repository;

import com.baccarin.gerenciador_usuarios.domain.Usuario;
import com.baccarin.gerenciador_usuarios.filtros.UsuarioFiltro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> , JpaSpecificationExecutor<Usuario> {

}
