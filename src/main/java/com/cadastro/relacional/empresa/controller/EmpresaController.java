package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import com.cadastro.relacional.empresa.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(description = "realiza cadastro de empresa no sistema")
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        return ResponseEntity.ok(empresaService.cadastrar(empresaRequestDTO));
    }

    @Operation(description = "atualiza cadastro de empresa no sistema")
    @PutMapping
    public ResponseEntity<EmpresaResponseDTO> atualizar(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO, @RequestParam String cnpj) {
        return ResponseEntity.ok(empresaService.atualizar(empresaRequestDTO, cnpj));
    }

    @Operation(description = "exclui cadastro de empresa no sistema")
    @DeleteMapping
    public ResponseEntity<Void> excluir(@RequestParam String cnpj) {
        empresaService.excluir(cnpj);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "busca cadastro por cnpj da empresa no sistema")
    @GetMapping("/buscar-por-cnpj")
    public ResponseEntity<EmpresaResponseDTO> buscarPorCnpj(@RequestParam String cnpj) {
        return ResponseEntity.ok(empresaService.buscarPorCNPJ(cnpj));
    }

    @Operation(description = "busca empresas cadastradas no sistema")
    @GetMapping
    public ResponseEntity<Page<EmpresaResponseDTO>> obterTodasEmpresas(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "20") Integer quantidadeDeRegistros) {
       return ResponseEntity.ok(empresaService.listarTodasEmpresas(pagina, quantidadeDeRegistros));
    }

    @Operation(description = "busca empresas cadastradas por nome fantasia ou cidade no sistema")
    @GetMapping("/buscar-por-nome-fantasia-ou-cidade")
    public ResponseEntity<Page<EmpresaResponseDTO>> obterEmpresasPorNomeFantasiaOuCidade(@RequestParam(defaultValue = "0") Integer pagina,
                                                                                         @RequestParam(defaultValue = "20") Integer quantidadeDeRegistros,
                                                                                         @RequestParam(required = false, defaultValue = "") String nomeFantasia,
                                                                                         @RequestParam(required = false, defaultValue = "") String cidade) {
        return ResponseEntity.ok(empresaService.listarEmpresasPorNomeFantasiaOuCidade(pagina, quantidadeDeRegistros, nomeFantasia, cidade));

    }





}
