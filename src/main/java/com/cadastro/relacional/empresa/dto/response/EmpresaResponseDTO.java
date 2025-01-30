package com.cadastro.relacional.empresa.dto.response;

import com.cadastro.relacional.empresa.entity.enums.SituacaoEmpresa;

import java.time.LocalDate;

public record EmpresaResponseDTO(
        Long id,
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        String telefone,
        LocalDate dataFundacao,
        String email,
        SituacaoEmpresa situacaoEmpresa,
        EnderecoResponseDTO endereco

) {
}
