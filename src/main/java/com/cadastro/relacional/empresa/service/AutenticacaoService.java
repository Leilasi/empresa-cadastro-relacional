package com.cadastro.relacional.empresa.service;

import com.cadastro.relacional.empresa.dto.request.UsuarioAutenticacaoRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioAutentificadoResponseDTO;

public interface AutenticacaoService {
    UsuarioAutentificadoResponseDTO login(UsuarioAutenticacaoRequestDTO usuarioAutenticacaoRequestDTO);
}
