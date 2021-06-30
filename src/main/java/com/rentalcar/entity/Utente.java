package com.rentalcar.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "datadinascita")
    private Date datadinascita;

    @ManyToOne
    @JoinColumn(name = "ruolo", nullable = false)
    private TipologiaUtente ruolo;

    @OneToMany(mappedBy = "utente")
    private Set<Prenotazioni> prenotazioni;

    public Utente(){}

    public Utente(String nome, String cognome, Date datadinascita, TipologiaUtente ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.ruolo = ruolo;
    }

    public Utente(Long id, String nome, String cognome, Date datadinascita, TipologiaUtente ruolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.ruolo = ruolo;
    }



    public Long  getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getRuolo() {
        return ruolo.getRuolo();
    }

    public void setRuolo(TipologiaUtente ruolo) {
        this.ruolo = ruolo;
    }

    public Set<Prenotazioni> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazioni> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", data di nascita=" + this.getDatadinascita() + ", ruolo=" + this.getRuolo() + "]";
    }
}
