package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.dto.request.UsuarioAutenticacaoRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioAutentificadoResponseDTO;
import com.cadastro.relacional.empresa.service.AutenticacaoService;
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

    @PostMapping("/login")
    public ResponseEntity<UsuarioAutentificadoResponseDTO> login(@RequestBody @Valid UsuarioAutenticacaoRequestDTO usuarioAutenticacaoRequestDTO) {
       return ResponseEntity.ok(autenticacaoService.login(usuarioAutenticacaoRequestDTO));
    }
}
