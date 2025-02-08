package com.cadastro.relacional.empresa.entity.enums;

public enum PerfilUsuario {
    ADMIN (0, "Administrador", "ROLE_ADMIN"),
    OPERACIONAL (1, "Operacional", "ROLE_OPERACIONAL");

    private final int codigo;
    private final String descricao;
    private final String descricaoRole;

    PerfilUsuario(int codigo, String descricao, String descricaoRole) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.descricaoRole = descricaoRole;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDescricaoRole() {
        return descricaoRole;
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
