package com.rentalcar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipologia_automezzo")
public class TipologiaAutomezzo {

    @Id
    @Column(name = "categoria")
    private String categoria;

    public TipologiaAutomezzo() {
    }

    public TipologiaAutomezzo(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
