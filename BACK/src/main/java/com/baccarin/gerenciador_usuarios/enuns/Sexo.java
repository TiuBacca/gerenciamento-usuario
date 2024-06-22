package com.baccarin.gerenciador_usuarios.enuns;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {

    MASCULINO("Masculino"), FEMININO("Feminino"), OUTRO("Outro");

    private String descricao;

    @Override
    public String toString(){
        return this.getDescricao();
    }

}
