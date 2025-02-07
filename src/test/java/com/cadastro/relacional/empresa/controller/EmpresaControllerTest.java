package com.cadastro.relacional.empresa.controller;

import com.cadastro.relacional.empresa.controller.exception.handler.GlobalExceptionHandler;
import com.cadastro.relacional.empresa.dto.request.EmpresaRequestDTO;
import com.cadastro.relacional.empresa.dto.response.EmpresaResponseDTO;
import com.cadastro.relacional.empresa.dto.response.EnderecoResponseDTO;
import com.cadastro.relacional.empresa.entity.enums.SituacaoEmpresa;
import com.cadastro.relacional.empresa.service.EmpresaService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmpresaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    private EmpresaResponseDTO empresaResponseDTO;

    @JsonIgnoreProperties({"pageable"})
    private abstract static class PageImplMixin {
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(empresaController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO(
                1L, "Rua Exemplo", "123", "Apto 45", "Bairro Exemplo", "Cidade Exemplo", "SP", "12345678"
        );

        LocalDate dataFundacao = LocalDate.parse("2025-01-01");
        SituacaoEmpresa situacao = SituacaoEmpresa.toEnum("A");

        empresaResponseDTO = new EmpresaResponseDTO();
        empresaResponseDTO.setCnpj("38680589000199");
        empresaResponseDTO.setRazaoSocial("Razão Social Exemplo");
        empresaResponseDTO.setNomeFantasia("Nome Fantasia Exemplo");
        empresaResponseDTO.setTelefone("1122334455");
        empresaResponseDTO.setEmail("empresa@exemplo.com");
        empresaResponseDTO.setDataFundacao(dataFundacao);
        empresaResponseDTO.setSituacaoEmpresa(situacao);
        empresaResponseDTO.setEndereco(enderecoResponseDTO);
    }

    @Test
    void testCadastrarEmpresa() throws Exception {
        when(empresaService.cadastrar(any(EmpresaRequestDTO.class))).thenReturn(empresaResponseDTO);

        mockMvc.perform(post("/empresas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"cnpj\": \"38680589000199\",\n" +
                                "  \"razaoSocial\": \"Razão Social Exemplo\",\n" +
                                "  \"nomeFantasia\": \"Nome Fantasia Exemplo\",\n" +
                                "  \"telefone\": \"1122334455\",\n" +
                                "  \"email\": \"empresa@exemplo.com\",\n" +
                                "  \"dataFundacao\": \"2025-01-01\",\n" +
                                "  \"situacaoEmpresa\": \"ATIVO\",\n" +
                                "  \"endereco\": {\n" +
                                "    \"id\": 1,\n" +
                                "    \"rua\": \"Rua Exemplo\",\n" +
                                "    \"numero\": \"123\",\n" +
                                "    \"complemento\": \"Apto 45\",\n" +
                                "    \"bairro\": \"Bairro Exemplo\",\n" +
                                "    \"cidade\": \"Cidade Exemplo\",\n" +
                                "    \"estado\": \"SP\",\n" +
                                "    \"cep\": \"12345678\"\n" +
                                "  }\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cnpj").value("38680589000199"))
                .andExpect(jsonPath("$.razaoSocial").value("Razão Social Exemplo"))
                .andExpect(jsonPath("$.nomeFantasia").value("Nome Fantasia Exemplo"))
                .andExpect(jsonPath("$.telefone").value("1122334455"))
                .andExpect(jsonPath("$.email").value("empresa@exemplo.com"))
                .andExpect(jsonPath("$.dataFundacao").value("2025-01-01"))
                .andExpect(jsonPath("$.situacaoEmpresa").value("ATIVO"))
                .andExpect(jsonPath("$.endereco.rua").value("Rua Exemplo"))
                .andExpect(jsonPath("$.endereco.numero").value("123"))
                .andExpect(jsonPath("$.endereco.complemento").value("Apto 45"))
                .andExpect(jsonPath("$.endereco.bairro").value("Bairro Exemplo"))
                .andExpect(jsonPath("$.endereco.cidade").value("Cidade Exemplo"))
                .andExpect(jsonPath("$.endereco.estado").value("SP"))
                .andExpect(jsonPath("$.endereco.cep").value("12345678"));
    }

    @Test
    void testAtualizarEmpresa() throws Exception {
        EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO(
                1L,
                "38680589000199",
                "Razão Social Exemplo Atualizada",
                "Nome Fantasia Exemplo Atualizado",
                "1122334455",
                LocalDate.of(2025, 1, 1),
                "empresa@exemplo.com",
                SituacaoEmpresa.ATIVO,
                new EnderecoResponseDTO(
                        1L,
                        "Rua Exemplo Atualizada",
                        "123",
                        "Apto 45",
                        "Bairro Exemplo Atualizado",
                        "Cidade Exemplo Atualizada",
                        "SP",
                        "12345678"
                )
        );
        when(empresaService.atualizar(any(EmpresaRequestDTO.class), eq("38680589000199"))).thenReturn(empresaResponseDTO);

        mockMvc.perform(put("/empresas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("cnpj", "38680589000199")
                        .content("{\n" +
                                "  \"cnpj\": \"38680589000199\",\n" +
                                "  \"razaoSocial\": \"Razão Social Exemplo Atualizada\",\n" +
                                "  \"nomeFantasia\": \"Nome Fantasia Exemplo Atualizado\",\n" +
                                "  \"telefone\": \"1122334455\",\n" +
                                "  \"email\": \"empresa@exemplo.com\",\n" +
                                "  \"dataFundacao\": \"2025-01-01\",\n" +
                                "  \"situacaoEmpresa\": \"ATIVO\",\n" +
                                "  \"endereco\": {\n" +
                                "    \"id\": 1,\n" +
                                "    \"rua\": \"Rua Exemplo Atualizada\",\n" +
                                "    \"numero\": \"123\",\n" +
                                "    \"complemento\": \"Apto 45\",\n" +
                                "    \"bairro\": \"Bairro Exemplo Atualizado\",\n" +
                                "    \"cidade\": \"Cidade Exemplo Atualizada\",\n" +
                                "    \"estado\": \"SP\",\n" +
                                "    \"cep\": \"12345678\"\n" +
                                "  }\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cnpj").value("38680589000199"))
                .andExpect(jsonPath("$.razaoSocial").value("Razão Social Exemplo Atualizada"))
                .andExpect(jsonPath("$.nomeFantasia").value("Nome Fantasia Exemplo Atualizado"))
                .andExpect(jsonPath("$.telefone").value("1122334455"))
                .andExpect(jsonPath("$.email").value("empresa@exemplo.com"))
                .andExpect(jsonPath("$.dataFundacao").value("2025-01-01"))
                .andExpect(jsonPath("$.situacaoEmpresa").value("ATIVO"))
                .andExpect(jsonPath("$.endereco.rua").value("Rua Exemplo Atualizada"))
                .andExpect(jsonPath("$.endereco.numero").value("123"))
                .andExpect(jsonPath("$.endereco.complemento").value("Apto 45"))
                .andExpect(jsonPath("$.endereco.bairro").value("Bairro Exemplo Atualizado"))
                .andExpect(jsonPath("$.endereco.cidade").value("Cidade Exemplo Atualizada"))
                .andExpect(jsonPath("$.endereco.estado").value("SP"))
                .andExpect(jsonPath("$.endereco.cep").value("12345678"));
    }

    @Test
    void testExcluirEmpresa() throws Exception {
        doNothing().when(empresaService).excluir(eq("38680589000199"));

        mockMvc.perform(delete("/empresas")
                        .param("cnpj", "38680589000199"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testBuscarPorCnpj() throws Exception {
        EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO(
                1L,
                "38680589000199",
                "Razão Social Exemplo",
                "Nome Fantasia Exemplo",
                "1122334455",
                LocalDate.of(2025, 1, 1),
                "empresa@exemplo.com",
                SituacaoEmpresa.ATIVO,
                new EnderecoResponseDTO(
                        1L,
                        "Rua Exemplo",
                        "123",
                        "Apto 45",
                        "Bairro Exemplo",
                        "Cidade Exemplo",
                        "SP",
                        "12345678"
                )
        );

        when(empresaService.buscarPorCNPJ(eq("38680589000199"))).thenReturn(empresaResponseDTO);

        mockMvc.perform(get("/empresas/buscar-por-cnpj")
                        .param("cnpj", "38680589000199"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cnpj").value("38680589000199"))
                .andExpect(jsonPath("$.razaoSocial").value("Razão Social Exemplo"))
                .andExpect(jsonPath("$.nomeFantasia").value("Nome Fantasia Exemplo"))
                .andExpect(jsonPath("$.telefone").value("1122334455"))
                .andExpect(jsonPath("$.email").value("empresa@exemplo.com"))
                .andExpect(jsonPath("$.dataFundacao").value("2025-01-01"))
                .andExpect(jsonPath("$.situacaoEmpresa").value("ATIVO"))
                .andExpect(jsonPath("$.endereco.rua").value("Rua Exemplo"))
                .andExpect(jsonPath("$.endereco.numero").value("123"))
                .andExpect(jsonPath("$.endereco.complemento").value("Apto 45"))
                .andExpect(jsonPath("$.endereco.bairro").value("Bairro Exemplo"))
                .andExpect(jsonPath("$.endereco.cidade").value("Cidade Exemplo"))
                .andExpect(jsonPath("$.endereco.estado").value("SP"))
                .andExpect(jsonPath("$.endereco.cep").value("12345678"));
    }


    private void configuraInicioTestesComPaginacao(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.addMixIn(PageImpl.class, PageImplMixin.class);

        mockMvc = MockMvcBuilders
                .standaloneSetup(empresaController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    void testObterTodasEmpresas() throws Exception {
        configuraInicioTestesComPaginacao();

        List<EmpresaResponseDTO> empresas = Arrays.asList(
                new EmpresaResponseDTO(
                        1L,
                        "38680589000199",
                        "Razão Social Exemplo 1",
                        "Nome Fantasia Exemplo 1",
                        "1122334455",
                        LocalDate.of(2025, 1, 1),
                        "empresa1@exemplo.com",
                        SituacaoEmpresa.ATIVO,
                        new EnderecoResponseDTO(
                                1L,
                                "Rua Exemplo 1",
                                "123",
                                "Apto 45",
                                "Bairro Exemplo 1",
                                "Cidade Exemplo 1",
                                "SP",
                                "12345678"
                        )
                ),
                new EmpresaResponseDTO(
                        2L,
                        "38680589000200",
                        "Razão Social Exemplo 2",
                        "Nome Fantasia Exemplo 2",
                        "1122334456",
                        LocalDate.of(2025, 2, 2),
                        "empresa2@exemplo.com",
                        SituacaoEmpresa.ATIVO,
                        new EnderecoResponseDTO(
                                2L,
                                "Rua Exemplo 2",
                                "124",
                                "Apto 46",
                                "Bairro Exemplo 2",
                                "Cidade Exemplo 2",
                                "SP",
                                "12345679"
                        )
                )
        );

        Page<EmpresaResponseDTO> page = new PageImpl<>(empresas);

        when(empresaService.listarTodasEmpresas(0, 20)).thenReturn(page);

        mockMvc.perform(get("/empresas")
                        .param("pagina", "0")
                        .param("quantidadeDeRegistros", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(2))
                .andExpect(jsonPath("$.content[0].cnpj").value("38680589000199"))
                .andExpect(jsonPath("$.content[0].razaoSocial").value("Razão Social Exemplo 1"))
                .andExpect(jsonPath("$.content[1].cnpj").value("38680589000200"))
                .andExpect(jsonPath("$.content[1].razaoSocial").value("Razão Social Exemplo 2"));
    }

    @Test
    void testObterEmpresasPorNomeFantasiaOuCidade() throws Exception {
        configuraInicioTestesComPaginacao();

        List<EmpresaResponseDTO> empresas = Arrays.asList(
                new EmpresaResponseDTO(
                        1L,
                        "38680589000199",
                        "Razão Social Exemplo 1",
                        "Nome Fantasia Exemplo 1",
                        "1122334455",
                        LocalDate.of(2025, 1, 1),
                        "empresa1@exemplo.com",
                        SituacaoEmpresa.ATIVO,
                        new EnderecoResponseDTO(
                                1L,
                                "Rua Exemplo 1",
                                "123",
                                "Apto 45",
                                "Bairro Exemplo 1",
                                "Cidade Exemplo 1",
                                "SP",
                                "12345678"
                        )
                ),
                new EmpresaResponseDTO(
                        2L,
                        "38680589000200",
                        "Razão Social Exemplo 2",
                        "Nome Fantasia Exemplo 2",
                        "1122334456",
                        LocalDate.of(2025, 2, 2),
                        "empresa2@exemplo.com",
                        SituacaoEmpresa.ATIVO,
                        new EnderecoResponseDTO(
                                2L,
                                "Rua Exemplo 2",
                                "124",
                                "Apto 46",
                                "Bairro Exemplo 2",
                                "Cidade Exemplo 2",
                                "SP",
                                "12345679"
                        )
                )
        );

        Page<EmpresaResponseDTO> page = new PageImpl<>(empresas);

        when(empresaService.listarEmpresasPorNomeFantasiaOuCidade(0, 20, "Nome Fantasia Exemplo", "Cidade Exemplo"))
                .thenReturn(page);

        mockMvc.perform(get("/empresas/buscar-por-nome-fantasia-ou-cidade")
                        .param("pagina", "0")
                        .param("quantidadeDeRegistros", "20")
                        .param("nomeFantasia", "Nome Fantasia Exemplo")
                        .param("cidade", "Cidade Exemplo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(2))
                .andExpect(jsonPath("$.content[0].cnpj").value("38680589000199"))
                .andExpect(jsonPath("$.content[0].nomeFantasia").value("Nome Fantasia Exemplo 1"))
                .andExpect(jsonPath("$.content[1].cnpj").value("38680589000200"))
                .andExpect(jsonPath("$.content[1].nomeFantasia").value("Nome Fantasia Exemplo 2"));
    }




}
