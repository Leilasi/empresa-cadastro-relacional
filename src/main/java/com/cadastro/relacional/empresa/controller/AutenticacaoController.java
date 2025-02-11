package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.dto.request.UsuarioAutenticacaoRequestDTO;
import com.cadastro.relacional.empresa.dto.request.UsuarioRequestDTO;
import com.cadastro.relacional.empresa.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacoes")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody @Valid UsuarioAutenticacaoRequestDTO usuarioAutenticacaoRequestDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(
                usuarioAutenticacaoRequestDTO.getEmail(),
                usuarioAutenticacaoRequestDTO.getSenha()
        );
       var authentication = authenticationManager.authenticate(userNamePassword);
       var token = tokenService.gerarToken(authentication.getName());
       return ResponseEntity.ok(token);
    }
}
