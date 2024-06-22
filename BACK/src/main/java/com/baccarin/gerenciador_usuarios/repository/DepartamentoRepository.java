package com.baccarin.gerenciador_usuarios.repository;

import com.baccarin.gerenciador_usuarios.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
