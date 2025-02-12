package com.cadastro.relacional.empresa.service.impl;

import com.cadastro.relacional.empresa.dto.request.UsuarioAutenticacaoRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioAutentificadoResponseDTO;
import com.cadastro.relacional.empresa.service.AutenticacaoService;
import com.cadastro.relacional.empresa.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UsuarioAutentificadoResponseDTO login(UsuarioAutenticacaoRequestDTO usuarioAutenticacaoRequestDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(
                usuarioAutenticacaoRequestDTO.getEmail(),
                usuarioAutenticacaoRequestDTO.getSenha()
        );
        var authentication = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.gerarToken(authentication.getName());
        return new UsuarioAutentificadoResponseDTO(token);
    }


}
