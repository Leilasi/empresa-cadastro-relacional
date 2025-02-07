package com.cadastro.relacional.empresa.service.impl;

import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import com.cadastro.relacional.empresa.entity.Empresa;
import com.cadastro.relacional.empresa.mapper.GenericMapper;
import com.cadastro.relacional.empresa.repository.EmpresaRepository;
import com.cadastro.relacional.empresa.service.EmpresaService;
import com.cadastro.relacional.empresa.service.exception.EmpresaNotFoundException;
import com.cadastro.relacional.empresa.service.exception.InvalidEmpresaException;
import com.cadastro.relacional.empresa.service.exception.InvalidParameterException;
import com.cadastro.relacional.empresa.utils.CNPJFormatterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaServiceImpl.class);

    @Autowired
    private  EmpresaRepository empresaRepository;

    @Autowired
    private GenericMapper mapper;

    @Override
    @Transactional
    public EmpresaResponseDTO cadastrar(EmpresaRequestDTO empresaRequestDTO) {
        if (empresaRepository.existsByCnpj(empresaRequestDTO.getCnpj())) {
            throw new InvalidEmpresaException("CNPJ já se encontra cadastrado em outra empresa");
        }
        Empresa empresa = mapper.dtoParaEntidade(empresaRequestDTO, Empresa.class);
        empresa.setCnpj(CNPJFormatterUtil.formatCNPJ(empresa.getCnpj()));
        empresa = empresaRepository.save(empresa);
        LOGGER.info("empresa registrada com sucesso");
        return mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class);
    }

    @Override
    @Transactional
    public EmpresaResponseDTO atualizar(EmpresaRequestDTO empresaRequestDTO, String cnpj) {
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);
        Empresa empresaPersistida = empresaRepository.findByCnpj(cnpjFormatado)
                .orElseThrow(() -> new EmpresaNotFoundException("empresa com cnpj " + cnpjFormatado + " não encontrada " ));
        if (!empresaPersistida.getCnpj().equals(CNPJFormatterUtil.formatCNPJ(empresaRequestDTO.getCnpj()))) {
            throw new InvalidEmpresaException("CNPJ não pode ser atualizado ");
        }
        BeanUtils.copyProperties(empresaRequestDTO, empresaPersistida);
        Empresa empresa = empresaRepository.save(empresaPersistida);
        LOGGER.info("empresa atualizada com sucesso");
        return mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class);
    }

    @Override
    public EmpresaResponseDTO buscarPorCNPJ(String cnpj) {
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);
        Empresa empresa = empresaRepository.findByCnpj(cnpjFormatado)
                .orElseThrow(() -> new EmpresaNotFoundException("empresa com cnpj " + cnpjFormatado + " não encontrada " ));
        LOGGER.info("empresa encontrada com sucesso na busca por CNPJ");
        return mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class);
    }

    @Override
    public Page<EmpresaResponseDTO> listarTodasEmpresas(Integer pagina, Integer quantidadeRegistros) {
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros);
        Page<Empresa> empresas = empresaRepository.findAll(pageable);
        return mapper.entidadeParaDTO(empresas, EmpresaResponseDTO.class, pageable);
    }

    @Override
    public Page<EmpresaResponseDTO> listarEmpresasPorNomeFantasiaOuCidade(Integer pagina, Integer quantidadeRegistros, String nomeFantasia, String cidade) {
        if ((nomeFantasia == null || nomeFantasia.isEmpty()) && (cidade  == null || cidade.isEmpty())) {
            throw new InvalidParameterException("Nome fantasia ou cidade da empresa deve ser informado");
        }
        Pageable pageable = PageRequest.of(pagina, quantidadeRegistros);
        Page<Empresa> empresas = empresaRepository.findByNomeFantasiaOrEnderecoCidade(nomeFantasia, cidade, pageable);
        return mapper.entidadeParaDTO(empresas, EmpresaResponseDTO.class, pageable);
    }

    @Override
    @Transactional
    public void excluir(String cnpj) {
        if (cnpj == null || cnpj.isEmpty())  {
            throw new InvalidParameterException("CNPJ da empresa não pode ser nulo ou vazio");
        }
        String cnpjFormatado = CNPJFormatterUtil.formatCNPJ(cnpj);
        empresaRepository.deleteByCnpj(cnpjFormatado);
        LOGGER.info("empresa excluida com sucesso");
    }




}
