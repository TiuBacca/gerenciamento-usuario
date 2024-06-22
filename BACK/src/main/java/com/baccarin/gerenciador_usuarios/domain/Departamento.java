package com.baccarin.gerenciador_usuarios.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "departamento", schema = "gerenciamento-usuarios")
@Entity
public class Departamento {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

}
