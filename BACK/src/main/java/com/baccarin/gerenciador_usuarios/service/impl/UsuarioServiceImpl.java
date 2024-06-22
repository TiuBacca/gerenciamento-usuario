package com.baccarin.gerenciador_usuarios.service.impl;

import com.baccarin.gerenciador_usuarios.domain.Departamento;
import com.baccarin.gerenciador_usuarios.domain.Documento;
import com.baccarin.gerenciador_usuarios.domain.Usuario;
import com.baccarin.gerenciador_usuarios.exceptions.RegistroIncompletoException;
import com.baccarin.gerenciador_usuarios.exceptions.RegistroNaoEncontradoException;
import com.baccarin.gerenciador_usuarios.filtros.UsuarioFiltro;
import com.baccarin.gerenciador_usuarios.repository.DepartamentoRepository;
import com.baccarin.gerenciador_usuarios.repository.DocumentoRepository;
import com.baccarin.gerenciador_usuarios.repository.UsuarioRepository;
import com.baccarin.gerenciador_usuarios.request.UsuarioRequest;
import com.baccarin.gerenciador_usuarios.service.UsuarioService;
import com.baccarin.gerenciador_usuarios.specifications.UsuarioSpecifications;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DocumentoRepository documentoRepository;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public List<Usuario> findByFiltro(UsuarioFiltro filtro) {
        return usuarioRepository.findAll(UsuarioSpecifications.comDocumentoENome(filtro.getDocumento(), filtro.getNomeUsuario()));
    }

    @Override
    public void salvarUsuario(UsuarioRequest request) throws Exception {
        validaSalvarUsuario(request);
        Usuario usuario = usuarioRepository.findById(request.getId())
                .map(existingUsuario -> {
                    existingUsuario.setNome(request.getNome());
                    existingUsuario.setSexo(request.getSexo());
                    return existingUsuario;
                })
                .orElseGet(() -> {
                    Usuario newUser = new Usuario();
                    newUser.setNome(request.getNome());
                    newUser.setSexo(request.getSexo());
                    return newUser;
                });

//        if (Objects.nonNull(request.getDocumento()) && ( Objects.nonNull(request.getDocumento().getTipo()) || StringUtils.isNotBlank(request.getDocumento().getDocumento()))){
//            Documento doc = Documento.builder().id(request.getDocumento().getId()).documento(request.getDocumento().getDocumento()).build();
//            doc = documentoRepository.save(doc);
//            usuario.setDocumento(doc);
//        }
//
//        if (Objects.nonNull(request.getDepartamento()) && ( Objects.nonNull(request.getDepartamento().getId()) || StringUtils.isNotBlank(request.getDepartamento().getNome()))){
//            Departamento dep = Departamento.builder().id(request.getDepartamento().getId()).nome(request.getDepartamento().getNome()).build();
//            dep = departamentoRepository.save(dep);
//            usuario.setDepartamento(dep);
//        }

        if (request.getDocumento() != null && (request.getDocumento().getId() != null || StringUtils.isNotBlank(request.getDocumento().getDocumento()))) {
            Documento documento = request.getDocumento().getId() != null ?
                    documentoRepository.findById(request.getDocumento().getId())
                            .orElseGet(Documento::new) :
                    new Documento();

            documento.setDocumento(request.getDocumento().getDocumento());
            documento = documentoRepository.save(documento);

            usuario.setDocumento(documento);
        } else {
            usuario.setDocumento(null);
        }

        if (request.getDepartamento() != null && (request.getDepartamento().getId() != null || StringUtils.isNotBlank(request.getDepartamento().getNome()))) {
            Departamento departamento = request.getDepartamento().getId() != null ?
                    departamentoRepository.findById(request.getDepartamento().getId())
                            .orElseGet(Departamento::new) :
                    new Departamento();

            departamento.setNome(request.getDepartamento().getNome());
            departamento = departamentoRepository.save(departamento);

            usuario.setDepartamento(departamento);
        } else {
            usuario.setDepartamento(null);
        }

        usuarioRepository.save(usuario);
    }

    @Override
    public void removerUsuario(UsuarioRequest request) throws Exception {
        validaRemoverUsuario(request);
        usuarioRepository.deleteById(request.getId());
    }

    @Override
    public void associarDepartamento(UsuarioRequest request) throws Exception {
        validaAssociarDepartamento(request);
        Usuario usuario = usuarioRepository.findById(request.getId()).get();
        usuario.setDepartamento(Departamento.builder().id(request.getDepartamento().getId()).build());
        usuarioRepository.save(usuario);
    }

    void validaSalvarUsuario(UsuarioRequest request) throws Exception {
        if (Objects.nonNull(request.getId()) && request.getId() != 0L) {
            usuarioRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não foi encontrado!"));
        } else {
            if (StringUtils.isBlank(request.getNome())) {
                throw new RegistroIncompletoException("O nome do usuário deve ser informado!");
            } else {
                if (Objects.isNull(request.getDocumento())) {
                    throw new RegistroIncompletoException("O documento deve ser informado!");
                } else if (StringUtils.isBlank(request.getDocumento().getDocumento())) {
                    throw new RegistroIncompletoException("O número do documento deve ser informado!");
                } else if (Objects.isNull(request.getDocumento().getTipo())) {
                    throw new RegistroIncompletoException("O tipo do documento deve ser informado!");
                }
            }
        }
    }

    void validaRemoverUsuario(UsuarioRequest request) throws Exception {
        if (Objects.nonNull(request.getId()) && request.getId() != 0L) {
            usuarioRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não foi encontrado!"));
            return;
        }
        throw new RegistroIncompletoException("O usuário deve ser informado para remoção do mesmo!");
    }

    void validaAssociarDepartamento(UsuarioRequest request) throws Exception {
        if (Objects.nonNull(request.getId()) && request.getId() != 0L) {
            usuarioRepository.findById(request.getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não foi encontrado!"));
            if (Objects.nonNull(request.getDepartamento()) && request.getDepartamento().getId() != 0L) {
                departamentoRepository.findById(request.getDepartamento().getId()).orElseThrow(() -> new RegistroNaoEncontradoException("Departamento não foi encontrado!"));
            } else {
                throw new RegistroIncompletoException("O departamento deve ser informado para associação com usuário!");
            }
        } else {
            throw new RegistroIncompletoException("O usuário deve ser informado para associação com depertamento!");
        }
    }
}
