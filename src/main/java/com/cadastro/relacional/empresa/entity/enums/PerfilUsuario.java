package com.cadastro.relacional.empresa.entity.enums;

public enum PerfilUsuario {
    ADMIN (0, "Administrador"),
    OPERACIONAL (1, "Operacional");

    private final int codigo;
    private final String descricaoCompleta;

    PerfilUsuario(int codigo, String descricaoCompleta) {
        this.codigo = codigo;
        this.descricaoCompleta = descricaoCompleta;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public static PerfilUsuario toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (PerfilUsuario enumItem : PerfilUsuario.values()) {
            if (enumItem.getCodigo() == codigo) {
                return enumItem;
            }
        }
        throw new IllegalArgumentException("Perfil do usuário com código " + codigo + " não encontrado");
    }


}
