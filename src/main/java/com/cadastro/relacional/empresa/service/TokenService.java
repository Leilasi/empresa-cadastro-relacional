package com.cadastro.relacional.empresa.service;

public interface TokenService {

    String gerarToken(String login);
    String obterLoginUsuario(String token);


}
