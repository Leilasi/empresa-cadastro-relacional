package com.cadastro.relacional.empresa.service.impl;

import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import com.cadastro.relacional.empresa.entity.Empresa;
import com.cadastro.relacional.empresa.mapper.GenericMapper;
import com.cadastro.relacional.empresa.repository.EmpresaRepository;
import com.cadastro.relacional.empresa.service.EmpresaService;
import com.cadastro.relacional.empresa.service.exception.EmpresaNotFoundException;
import com.cadastro.relacional.empresa.service.exception.InvalidEmpresaUpdateException;
import com.cadastro.relacional.empresa.service.exception.InvalidParameterException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private  EmpresaRepository empresaRepository;

    @Autowired
    private GenericMapper mapper;

    @Override
    public EmpresaResponseDTO cadastrar(EmpresaRequestDTO empresaRequestDTO) {
        Empresa empresa = mapper.dtoParaEntidade(empresaRequestDTO, Empresa.class);
        empresa = empresaRepository.save(empresa);
        return mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class);
    }

    @Override
    public EmpresaResponseDTO atualizar(EmpresaRequestDTO empresaRequestDTO, String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EmpresaNotFoundException("empresa com cnpj " + cnpj + " não encontrada " ));
        if (!empresa.getCnpj().equals(empresaRequestDTO.getCnpj())) {
            throw new InvalidEmpresaUpdateException("CNPJ não pode ser atualizado ");
        }
        empresa = mapper.dtoParaEntidade(empresaRequestDTO, Empresa.class);
        empresa = empresaRepository.save(empresa);
        return mapper.entidadeParaDTO(empresa, EmpresaResponseDTO.class);
    }

    @Override
    public EmpresaResponseDTO buscarPorCNPJ(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EmpresaNotFoundException("empresa com cnpj " + cnpj + " não encontrada " ));
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
    public void excluir(String cnpj) {
        if (cnpj == null || cnpj.isEmpty())  {
            throw new InvalidParameterException("CNPJ da empresa não pode ser nulo ou vazio");
        }
        empresaRepository.deleteByCnpj(cnpj);
    }
}
