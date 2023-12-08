package br.unipar.trabalhosub.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaisDTO {

    @JsonProperty("Código")
    private Integer codigo;
    @JsonProperty("Descrição")
    private String descricao;

    public PaisDTO() { }

    public PaisDTO(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


