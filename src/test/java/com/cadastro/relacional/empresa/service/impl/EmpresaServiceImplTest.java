package com.cadastro.relacional.empresa.service.impl;

import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import com.cadastro.relacional.empresa.entity.Empresa;
import com.cadastro.relacional.empresa.mapper.GenericMapper;
import com.cadastro.relacional.empresa.repository.EmpresaRepository;
import com.cadastro.relacional.empresa.service.exception.EmpresaNotFoundException;
import com.cadastro.relacional.empresa.service.exception.InvalidEmpresaException;
import com.cadastro.relacional.empresa.service.exception.InvalidParameterException;
import com.cadastro.relacional.empresa.utils.CNPJFormatterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceImplTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private GenericMapper mapper;

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    private EmpresaRequestDTO empresaRequestDTO;
    private Empresa empresa;
    private EmpresaResponseDTO empresaResponseDTO;

    @BeforeEach
    void setUp() {
        empresaRequestDTO = new EmpresaRequestDTO();
        empresaRequestDTO.setCnpj("12345678000195");

        empresa = new Empresa();
        empresa.setCnpj(CNPJFormatterUtil.formatCNPJ("12345678000195"));

        empresaResponseDTO = new EmpresaResponseDTO();
    }

    @Test
    void deveCadastrarEmpresaComSucesso() {
        when(empresaRepository.existsByCnpj(empresaRequestDTO.getCnpj())).thenReturn(false);
        when(mapper.dtoParaEntidade(empresaRequestDTO, Empresa.class)).thenReturn(empresa);
        when(empresaRepository.save(empresa)).thenReturn(empresa);
        when(mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class)).thenReturn(empresaResponseDTO);

        EmpresaResponseDTO response = empresaService.cadastrar(empresaRequestDTO);

        assertNotNull(response);
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    void deveLancarExcecaoQuandoCnpjJaExiste() {
        when(empresaRepository.existsByCnpj(empresaRequestDTO.getCnpj())).thenReturn(true);

        assertThrows(InvalidEmpresaException.class, () -> empresaService.cadastrar(empresaRequestDTO));
        verify(empresaRepository, never()).save(any(Empresa.class));
    }

    @Test
    void deveAtualizarEmpresaComSucesso() {
        String cnpj = "12345678000195";
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);

        when(empresaRepository.findByCnpj(cnpjFormatado)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(empresa)).thenReturn(empresa);
        when(mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class)).thenReturn(empresaResponseDTO);

        EmpresaResponseDTO response = empresaService.atualizar(empresaRequestDTO, cnpj);

        assertNotNull(response);
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    void deveLancarExcecaoQuandoEmpresaNaoEncontradaParaAtualizacao() {
        String cnpj = "12345678000195";
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);

        when(empresaRepository.findByCnpj(cnpjFormatado)).thenReturn(Optional.empty());

        assertThrows(EmpresaNotFoundException.class, () -> empresaService.atualizar(empresaRequestDTO, cnpj));
        verify(empresaRepository, never()).save(any(Empresa.class));
    }

    @Test
    void deveLancarExcecaoQuandoCnpjDiferenteParaAtualizacao() {
        String cnpj = "12345678000195";
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);
        empresaRequestDTO.setCnpj("98765432000199");

        when(empresaRepository.findByCnpj(cnpjFormatado)).thenReturn(Optional.of(empresa));

        assertThrows(InvalidEmpresaException.class, () -> empresaService.atualizar(empresaRequestDTO, cnpj));
        verify(empresaRepository, never()).save(any(Empresa.class));
    }

    @Test
    void deveBuscarEmpresaPorCnpjComSucesso() {
        String cnpj = "12345678000195";
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);

        when(empresaRepository.findByCnpj(cnpjFormatado)).thenReturn(Optional.of(empresa));
        when(mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class)).thenReturn(empresaResponseDTO);

        EmpresaResponseDTO response = empresaService.buscarPorCNPJ(cnpj);

        assertNotNull(response);
        verify(empresaRepository, times(1)).findByCnpj(cnpjFormatado);
    }

    @Test
    void deveLancarExcecaoQuandoEmpresaNaoEncontradaPorCnpj() {
        String cnpj = "12345678000195";
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);

        when(empresaRepository.findByCnpj(cnpjFormatado)).thenReturn(Optional.empty());

        assertThrows(EmpresaNotFoundException.class, () -> empresaService.buscarPorCNPJ(cnpj));
        verify(empresaRepository, times(1)).findByCnpj(cnpjFormatado);
    }

    @Test
    void deveListarTodasEmpresasComSucesso() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Empresa> empresasPage = new PageImpl<>(List.of(empresa), pageable, 1);

        when(empresaRepository.findAll(pageable)).thenReturn(empresasPage);
        when(mapper.entidadeParaDTO(empresasPage, EmpresaResponseDTO.class, pageable)).thenReturn(new PageImpl<>(List.of(empresaResponseDTO), pageable, 1));

        Page<EmpresaResponseDTO> response = empresaService.listarTodasEmpresas(0, 10);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        verify(empresaRepository, times(1)).findAll(pageable);
    }

    @Test
    void deveLancarExcecaoQuandoNomeFantasiaOuCidadeNaoInformado() {
        assertThrows(InvalidParameterException.class, () -> empresaService.listarEmpresasPorNomeFantasiaOuCidade(0, 10, "", ""));
    }

    @Test
    void deveListarEmpresasPorNomeFantasiaOuCidadeComSucesso() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Empresa> empresasPage = new PageImpl<>(List.of(empresa), pageable, 1);
        String nomeFantasia = "Empresa X";
        String cidade = "São Paulo";

        when(empresaRepository.findByNomeFantasiaOrEnderecoCidade(nomeFantasia, cidade, pageable)).thenReturn(empresasPage);
        when(mapper.entidadeParaDTO(empresasPage, EmpresaResponseDTO.class, pageable)).thenReturn(new PageImpl<>(List.of(empresaResponseDTO), pageable, 1));

        Page<EmpresaResponseDTO> response = empresaService.listarEmpresasPorNomeFantasiaOuCidade(0, 10, nomeFantasia, cidade);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        verify(empresaRepository, times(1)).findByNomeFantasiaOrEnderecoCidade(nomeFantasia, cidade, pageable);
    }

    @Test
    void deveExcluirEmpresaComSucesso() {
        String cnpj = "12345678000195";
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);

        doNothing().when(empresaRepository).deleteByCnpj(cnpjFormatado);

        assertDoesNotThrow(() -> empresaService.excluir(cnpj));
        verify(empresaRepository, times(1)).deleteByCnpj(cnpjFormatado);
    }

    @Test
    void deveLancarExcecaoQuandoExcluirCnpjNuloOuVazio() {
        assertThrows(InvalidParameterException.class, () -> empresaService.excluir(""));
        assertThrows(InvalidParameterException.class, () -> empresaService.excluir(null));
        verify(empresaRepository, never()).deleteByCnpj(anyString());
    }

}
