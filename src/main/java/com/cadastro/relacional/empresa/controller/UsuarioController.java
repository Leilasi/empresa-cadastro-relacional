package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.dto.request.UsuarioRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioResponseDTO;
import com.cadastro.relacional.empresa.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> buscarTodosUsuarios(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "20") Integer quantidadeDeRegistros) {
        return ResponseEntity.ok(usuarioService.obterTodosUsuarios(pagina, quantidadeDeRegistros));
    }

    @GetMapping("/buscar-por-email")
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.obterPorEmail(email));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {

        return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuarioRequestDTO));
    }



}
