package com.cadastro.relacional.empresa.dto.request;

import com.cadastro.relacional.empresa.entity.enums.SituacaoEmpresa;

import java.time.LocalDate;

public class EmpresaRequestDTO {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private LocalDate dataFundacao;
    private String email;
    private SituacaoEmpresa situacaoEmpresa;
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
