package com.baccarin.gerenciador_usuarios.specifications;

import com.baccarin.gerenciador_usuarios.domain.Usuario;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UsuarioSpecifications {

    public static Specification<Usuario> comDocumentoENome(String numeroDocumento, String nome) {
        Specification<Usuario> specDocumento = (numeroDocumento != null) ? comDocumento(numeroDocumento) : null;
        Specification<Usuario> specNome = (nome != null) ? comNome(nome) : null;

        if (specDocumento != null && specNome != null) {
            return Specification.where(specDocumento).and(specNome);
        } else // retorna todos os registros
            return Objects.requireNonNullElseGet(specDocumento, () -> Objects.requireNonNullElseGet(specNome, () -> (root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));
    }

    public static Specification<Usuario> comDocumento(String numeroDocumento) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("documento").get("documento"), numeroDocumento);
    }

    public static Specification<Usuario> comNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }
}