package com.cadastro.relacional.empresa.dto.request;

import com.cadastro.relacional.empresa.entity.enums.SituacaoEmpresa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;
import java.time.LocalDate;

public class EmpresaRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O campo cnpj é obrigatório")
    @CNPJ(message = "O cnpj informado é inválido")
    private String cnpj;

    @NotEmpty(message = "O campo razão Social é obrigatório")
    private String razaoSocial;

    @NotEmpty(message = "O campo Nome Fantasia é obrigatório")
    private String nomeFantasia;

    @NotEmpty(message = "O campo telefone é obrigatório")
    @Pattern(regexp = "^\\(?\\d{2}\\)? ?9?\\d{4}-?\\d{4}$", message = "O Telefone informado é inválido")
    private String telefone;

    @NotNull(message = "O campo data fundação é obrigatório")
    private LocalDate dataFundacao;

    @NotEmpty(message = "O campo email é obrigatório")
    @Email(message = "o email informado é inválido")
    private String email;

    @NotNull(message = "O campo situação empresa é obrigatório")
    private SituacaoEmpresa situacaoEmpresa;

    @NotNull(message = "O endereço da empresa é obrigatório")
    @Valid
    private EnderecoRequestDTO endereco;

    public EmpresaRequestDTO() {
    }

    public EmpresaRequestDTO(String cnpj, String razaoSocial, String nomeFantasia, String telefone, LocalDate dataFundacao, String email, SituacaoEmpresa situacaoEmpresa, EnderecoRequestDTO endereco) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.dataFundacao = dataFundacao;
        this.email = email;
        this.situacaoEmpresa = situacaoEmpresa;
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SituacaoEmpresa getSituacaoEmpresa() {
        return situacaoEmpresa;
    }

    public void setSituacaoEmpresa(SituacaoEmpresa situacaoEmpresa) {
        this.situacaoEmpresa = situacaoEmpresa;
    }

    public EnderecoRequestDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequestDTO endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "EmpresaRequestDTO{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataFundacao=" + dataFundacao +
                ", email='" + email + '\'' +
                ", situacaoEmpresa=" + situacaoEmpresa +
                ", endereco=" + endereco +
                '}';
    }
}
