package com.baccarin.gerenciador_usuarios.resource;

import com.baccarin.gerenciador_usuarios.domain.Usuario;
import com.baccarin.gerenciador_usuarios.exceptions.RegistroIncompletoException;
import com.baccarin.gerenciador_usuarios.exceptions.RegistroNaoEncontradoException;
import com.baccarin.gerenciador_usuarios.filtros.UsuarioFiltro;
import com.baccarin.gerenciador_usuarios.request.UsuarioRequest;
import com.baccarin.gerenciador_usuarios.response.ResponseGenerico;
import com.baccarin.gerenciador_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    // Usado para salvar/atualizar usuários
    @PostMapping("salvar")
    public ResponseEntity<ResponseGenerico> salvarUsuario(@RequestBody UsuarioRequest request) {
        try {
            usuarioService.salvarUsuario(request);
            return new ResponseEntity<>(ResponseGenerico.builder().mensagem("Usuário salvo com sucesso.").build(), HttpStatus.OK);
        } catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
            return new ResponseEntity<>(ResponseGenerico.builder().mensagem(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseGenerico.builder().mensagem("Erro ao salvar usuário.").build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("buscaLista")
    public ResponseEntity<List<Usuario>> buscarListaDeUsuarioByFiltro(@RequestBody UsuarioFiltro filtro) {
        List<Usuario> usuarios = usuarioService.findByFiltro(filtro);
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("remover")
    public ResponseEntity<ResponseGenerico> removerUsuario(@RequestBody UsuarioRequest request) {
        try {
            usuarioService.removerUsuario(request);
            return new ResponseEntity<>(ResponseGenerico.builder().mensagem("Usuário removido com sucesso.").build(), HttpStatus.OK);
        } catch (RegistroIncompletoException | RegistroNaoEncontradoException e) {
            return new ResponseEntity<>(ResponseGenerico.builder().mensagem(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseGenerico.builder().mensagem("Erro ao remover usuário.").build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
