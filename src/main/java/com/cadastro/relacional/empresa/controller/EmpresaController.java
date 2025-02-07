package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import com.cadastro.relacional.empresa.service.EmpresaService;
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

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        return ResponseEntity.ok(empresaService.cadastrar(empresaRequestDTO));
    }

    @PutMapping
    public ResponseEntity<EmpresaResponseDTO> atualizar(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO, @RequestParam String cnpj) {
        return ResponseEntity.ok(empresaService.atualizar(empresaRequestDTO, cnpj));
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@RequestParam String cnpj) {
        empresaService.excluir(cnpj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-cnpj")
    public ResponseEntity<EmpresaResponseDTO> buscarPorCnpj(@RequestParam String cnpj) {
        return ResponseEntity.ok(empresaService.buscarPorCNPJ(cnpj));
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaResponseDTO>> obterTodasEmpresas(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "20") Integer quantidadeDeRegistros) {
       return ResponseEntity.ok(empresaService.listarTodasEmpresas(pagina, quantidadeDeRegistros));
    }

    @GetMapping("/buscar-por-nome-fantasia-ou-cidade")
    public ResponseEntity<Page<EmpresaResponseDTO>> obterEmpresasPorNomeFantasiaOuCidade(@RequestParam(defaultValue = "0") Integer pagina,
                                                                                         @RequestParam(defaultValue = "20") Integer quantidadeDeRegistros,
                                                                                         @RequestParam(required = false, defaultValue = "") String nomeFantasia,
                                                                                         @RequestParam(required = false, defaultValue = "") String cidade) {
        return ResponseEntity.ok(empresaService.listarEmpresasPorNomeFantasiaOuCidade(pagina, quantidadeDeRegistros, nomeFantasia, cidade));

    }





}
