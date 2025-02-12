package com.cadastro.relacional.empresa.dto.response;

import java.io.Serializable;

public class UsuarioAutentificadoResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    public UsuarioAutentificadoResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
