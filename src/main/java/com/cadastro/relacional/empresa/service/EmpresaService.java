package com.cadastro.relacional.empresa.service;

import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import org.springframework.data.domain.Page;

public interface EmpresaService {
    EmpresaResponseDTO cadastrar(EmpresaRequestDTO empresaRequestDTO);
    EmpresaResponseDTO atualizar(EmpresaRequestDTO empresaRequestDTO, String cnpj);
    EmpresaResponseDTO buscarPorCNPJ(String cnpj);
    Page<EmpresaResponseDTO> listarTodasEmpresas(Integer pagina, Integer quantidadeRegistros);
    Page<EmpresaResponseDTO> listarEmpresasPorNomeFantasiaOuCidade(Integer pagina, Integer quantidadeRegistros, String nomeFantasia,String cidade);
    void excluir(String cnpj);

}
