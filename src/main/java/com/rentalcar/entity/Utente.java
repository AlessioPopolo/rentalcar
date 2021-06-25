package com.rentalcar.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "datadinascita")
    private Date datadinascita;

    @ManyToOne
    @JoinColumn(name = "ruolo", nullable = false)
    private TipologiaUtente ruolo;

    public Utente(){}

    public Utente(String nome, String cognome, Date datadinascita, TipologiaUtente ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.ruolo = ruolo;
    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDatadinascita() {
        return datadinascita;
    }

    public void setDatadinascita(Date datadinascita) {
        this.datadinascita = datadinascita;
    }

    public TipologiaUtente getRuolo() {
        return ruolo;
    }

    public void setRuolo(TipologiaUtente ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormat = simpleDateFormat.format(datadinascita);
        return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", data di nascita=" + dataFormat + ", ruolo=" + ruolo.getRuolo() + "]";
    }
}
