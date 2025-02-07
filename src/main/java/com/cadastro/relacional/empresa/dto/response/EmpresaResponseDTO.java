package com.cadastro.relacional.empresa.dto.response;

import com.cadastro.relacional.empresa.entity.enums.SituacaoEmpresa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;


public class EmpresaResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
        private Long id;
        private String cnpj;
        private String razaoSocial;
        private String nomeFantasia;
        private String telefone;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate dataFundacao;
        private String email;
        private SituacaoEmpresa situacaoEmpresa;
        private EnderecoResponseDTO endereco;


    public EmpresaResponseDTO(Long id, String cnpj, String razaoSocial, String nomeFantasia, String telefone, LocalDate dataFundacao, String email, SituacaoEmpresa situacaoEmpresa, EnderecoResponseDTO endereco) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.dataFundacao = dataFundacao;
        this.email = email;
        this.situacaoEmpresa = situacaoEmpresa;
        this.endereco = endereco;
    }

    public EmpresaResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "EmpresaResponseDTO{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
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
