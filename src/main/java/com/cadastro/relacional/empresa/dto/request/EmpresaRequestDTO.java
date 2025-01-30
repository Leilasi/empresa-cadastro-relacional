package com.cadastro.relacional.empresa.dto.request;

import com.cadastro.relacional.empresa.dto.response.EnderecoResponseDTO;
import com.cadastro.relacional.empresa.entity.enums.SituacaoEmpresa;

import java.time.LocalDate;

public record EmpresaRequestDTO(
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        String telefone,
        LocalDate dataFundacao,
        String email,
        SituacaoEmpresa situacaoEmpresa,
        EnderecoRequestDTO endereco
) {
}
