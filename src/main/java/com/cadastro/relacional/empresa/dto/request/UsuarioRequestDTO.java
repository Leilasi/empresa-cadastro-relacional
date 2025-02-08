package com.cadastro.relacional.empresa.dto.request;

import com.cadastro.relacional.empresa.entity.enums.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UsuarioRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "o campo nome é um registro obrigatório")
    private String nome;

    @NotEmpty(message = "o campo email é um registro obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotEmpty(message = "o campo senha é um registro obrigatório")
    private String senha;

    @NotNull(message = "o campo perfil é um registro obrigatório")
    private PerfilUsuario perfil;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nome, String email, String senha, PerfilUsuario perfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "UsuarioRequestDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}
