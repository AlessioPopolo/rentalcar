package com.rentalcar.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipologia_utente")
public class TipologiaUtente {

    @Id
    @Column(name = "ruolo")
    private String ruolo;

    public TipologiaUtente() {
    }

    public TipologiaUtente(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getRuolo() {
        return ruolo;
    }
}
