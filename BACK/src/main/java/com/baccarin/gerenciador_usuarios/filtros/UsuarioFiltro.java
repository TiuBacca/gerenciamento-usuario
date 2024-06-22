package com.baccarin.gerenciador_usuarios.filtros;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioFiltro {

    private Long idUsuario;
    private String nomeUsuario;
    private Long idDocumento;
    private String documento;

}
