package com.example.application.data.entity;

import java.util.Date;

public class Atividades {

    private Long id;
    private String tituloAtividade;
    private Date dataAtividade;
    private String descAtividade;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTituloAtividade() {
        return tituloAtividade;
    }
    public void setTituloAtividade(String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }
    public Date getDataAtividade() {
        return dataAtividade;
    }
    public void setDataAtividade(Date dataAtividade) {
        this.dataAtividade = dataAtividade;
    }
    public String getDescAtividade() {
        return descAtividade;
    }
    public void setDescAtividade(String descAtividade) {
        this.descAtividade = descAtividade;
    }

    

}
