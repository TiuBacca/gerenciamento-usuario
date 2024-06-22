package com.baccarin.gerenciador_usuarios.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "documento", schema = "gerenciamento-usuarios")
@Entity
public class Documento {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "documento")
    private String documento;
}
