package com.cadastro.relacional.empresa.service;

import com.cadastro.relacional.empresa.dto.request.UsuarioRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    
    Page<UsuarioResponseDTO> obterTodosUsuarios(Integer pagina, Integer quantidadeRegistros);
    UsuarioResponseDTO obterPorEmail(String email);
    UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO);
}
