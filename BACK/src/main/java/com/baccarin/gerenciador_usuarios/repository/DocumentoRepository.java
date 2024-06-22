package com.baccarin.gerenciador_usuarios.repository;

import com.baccarin.gerenciador_usuarios.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
