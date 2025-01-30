package com.cadastro.relacional.empresa.dto.request;

public record EnderecoRequestDTO(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
