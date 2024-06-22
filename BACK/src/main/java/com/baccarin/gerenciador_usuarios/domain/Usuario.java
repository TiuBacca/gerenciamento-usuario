package com.baccarin.gerenciador_usuarios.domain;

import com.baccarin.gerenciador_usuarios.enuns.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario", schema = "gerenciamento-usuarios")
@Entity
public class Usuario {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;


}
