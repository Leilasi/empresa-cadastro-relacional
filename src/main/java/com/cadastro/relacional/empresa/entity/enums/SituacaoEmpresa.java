package com.cadastro.relacional.empresa.entity.enums;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum SituacaoEmpresa {
    ATIVO (0, "A", "Ativo"),
    INATIVO (1,"I", "Inativo");

    private final int codigo;
    private final String descricaoAbreviada;
    private final String descricaoCompleta;

    SituacaoEmpresa(int codigo, String descricaoAbreviada, String descricaoCompleta) {
        this.codigo = codigo;
        this.descricaoAbreviada = descricaoAbreviada;
        this.descricaoCompleta = descricaoCompleta;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricaoAbreviada() {
        return descricaoAbreviada;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;

    }

    public static SituacaoEmpresa toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (SituacaoEmpresa enumItem : SituacaoEmpresa.values()) {
            if (enumItem.getCodigo() == codigo) {
                return enumItem;
            }
        }
        throw new IllegalArgumentException("Situacão com código " + codigo + " não encontrada");
    }

    public static SituacaoEmpresa toEnum(String descricaoAbreviada) {
        if (descricaoAbreviada == null) {
            return null;
        }
        return Arrays.stream(SituacaoEmpresa.values())
                .filter(situacaoEmpresa -> situacaoEmpresa.getDescricaoAbreviada().equals(descricaoAbreviada))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Situação com descrição abreviada " + descricaoAbreviada + " não encontrada"));

    }

}
