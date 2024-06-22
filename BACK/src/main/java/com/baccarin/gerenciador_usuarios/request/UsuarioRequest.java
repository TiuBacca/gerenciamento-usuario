package com.baccarin.gerenciador_usuarios.request;

import com.baccarin.gerenciador_usuarios.enuns.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private Long id;
    private String nome;
    private DocumentoRequest documento;
    private Sexo sexo;
    private DepartamentoRequest departamento;
}
