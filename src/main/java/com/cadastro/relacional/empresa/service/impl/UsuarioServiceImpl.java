package com.cadastro.relacional.empresa.service.impl;

import com.cadastro.relacional.empresa.dto.request.UsuarioRequestDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioAutenticacaoResponseDTO;
import com.cadastro.relacional.empresa.dto.response.UsuarioResponseDTO;
import com.cadastro.relacional.empresa.entity.Usuario;
import com.cadastro.relacional.empresa.mapper.GenericMapper;
import com.cadastro.relacional.empresa.repository.UsuarioRepository;
import com.cadastro.relacional.empresa.service.UsuarioService;
import com.cadastro.relacional.empresa.service.exception.InvalidParameterException;
import com.cadastro.relacional.empresa.service.exception.InvalidUsuarioException;
import com.cadastro.relacional.empresa.service.exception.UsuarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private GenericMapper mapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Page<UsuarioResponseDTO> obterTodosUsuarios(Integer pagina, Integer quantidadeRegistros) {
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros);
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return mapper.entidadeParaDTO(usuarios, UsuarioResponseDTO.class, pageable);
    }

    @Override
    public UsuarioResponseDTO obterPorEmail(String email) {
        Usuario usuario = obterUsuarioPorEmail(email);
        LOGGER.info("Usuario encontrado com Sucesso: {}", email);
        return mapper.entidadeParaDTO(usuario, UsuarioResponseDTO.class);
    }

    private Usuario obterUsuarioPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new InvalidParameterException("Email não pode ser vazio");
        }
        Usuario usuario = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException("o usuário não encontrado"));
        return usuario;

    }

    @Override
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new InvalidUsuarioException("Já existe usuário cadastrado com email informado");
        }
        Usuario usuario = mapper.entidadeParaDTO(usuarioRequestDTO, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        LOGGER.info("Usuario cadastrado com Sucesso: {}", usuario.getEmail());
        return mapper.entidadeParaDTO(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioAutenticacaoResponseDTO obterPorLogin(String login) {
        Usuario usuario = obterUsuarioPorEmail(login);
        LOGGER.info("Usuario cadastrado com Sucesso: {}", usuario.getEmail());
        return mapper.entidadeParaDTO(usuario, UsuarioAutenticacaoResponseDTO.class);
    }

}
