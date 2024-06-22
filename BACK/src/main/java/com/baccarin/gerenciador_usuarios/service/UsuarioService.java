package com.baccarin.gerenciador_usuarios.service;

import com.baccarin.gerenciador_usuarios.domain.Usuario;
import com.baccarin.gerenciador_usuarios.filtros.UsuarioFiltro;
import com.baccarin.gerenciador_usuarios.request.UsuarioRequest;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findByFiltro(UsuarioFiltro filtro);

    void salvarUsuario(UsuarioRequest request) throws Exception;

    void removerUsuario(UsuarioRequest request) throws Exception;

    void associarDepartamento(UsuarioRequest request) throws Exception;
}
