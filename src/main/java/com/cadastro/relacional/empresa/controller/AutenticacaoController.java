package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.dto.request.UsuarioAutenticacaoRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioAutentificadoResponseDTO;
import com.cadastro.relacional.empresa.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacoes")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Operation(description = "realiza login de usuário por email e senha")
    @PostMapping("/login")
    public ResponseEntity<UsuarioAutentificadoResponseDTO> login(@RequestBody @Valid UsuarioAutenticacaoRequestDTO usuarioAutenticacaoRequestDTO) {
       return ResponseEntity.ok(autenticacaoService.login(usuarioAutenticacaoRequestDTO));
    }
}
