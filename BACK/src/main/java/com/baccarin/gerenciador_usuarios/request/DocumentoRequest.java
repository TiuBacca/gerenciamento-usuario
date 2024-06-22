package com.baccarin.gerenciador_usuarios.request;

import com.baccarin.gerenciador_usuarios.enuns.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoRequest {

    private Long id;
    private String documento;
    private TipoDocumento tipo;
}
