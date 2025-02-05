package com.cadastro.relacional.empresa.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDTO  {

   @NotEmpty(message = "O campo rua é obrigatório")
   private String rua;

   @NotEmpty(message = "O campo número é obrigatório")
   private String numero;
   private String complemento;

   @NotEmpty(message = "O campo bairro é obrigatório")
   private String bairro;

   @NotEmpty(message = "O campo cidade é obrigatório")
   private String cidade;

   @NotEmpty(message = "O campo estado é obrigatório")
   @Size(max = 2, min = 2, message = "O campo estado deve conter apenas {max} digitos")
   private String estado;

   @NotEmpty(message = "O campo cep é obrigatório")
   private String cep;

    public EnderecoRequestDTO() {
    }

    public EnderecoRequestDTO(String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "EnderecoRequestDTO{" +
                "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
