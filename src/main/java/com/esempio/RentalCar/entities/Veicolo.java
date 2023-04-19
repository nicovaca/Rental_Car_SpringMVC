package com.esempio.RentalCar.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "veicolo")
@XmlRootElement
public class Veicolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "casaCostruttrice")
    @NotEmpty(message = "{NotEmpty.Veicolo.casaCostruttrice.validator}")
    private String casaCostruttrice;
    @Column(name = "modello")
    @NotEmpty(message = "{NotEmpty.Veicolo.modello.validator}")
    private String modello;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoVeicolo")
    private TipoVeicolo tipoVeicolo;
    @Column(name = "annoImmatricolazione")
    @NotNull(message = "{NotEmpty.Veicolo.annoImmatricolazione.validator}")
    private int annoImmatricolazione;
    @Column(name = "targa")
    @NotEmpty(message = "{NotEmpty.Veicolo.targa.validator}")
    private String targa;


    @OneToMany(mappedBy = "veicolo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Veicolo() {
    }

    public Veicolo(String casaCostruttrice, String modello, TipoVeicolo tipoVeicolo, int annoImmatricolazione, String targa, Set<Prenotazione> prenotazioni) {
        this.casaCostruttrice = casaCostruttrice;
        this.modello = modello;
        this.tipoVeicolo = tipoVeicolo;
        this.annoImmatricolazione = annoImmatricolazione;
        this.targa = targa;
        this.prenotazioni = prenotazioni;
    }

    public Veicolo(Long id, String casaCostruttrice, String modello, TipoVeicolo tipoVeicolo, int annoImmatricolazione, String targa, Set<Prenotazione> prenotazioni) {
        this.id = id;
        this.casaCostruttrice = casaCostruttrice;
        this.modello = modello;
        this.tipoVeicolo = tipoVeicolo;
        this.annoImmatricolazione = annoImmatricolazione;
        this.targa = targa;
        this.prenotazioni = prenotazioni;
    }

    @XmlElement
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getCasaCostruttrice() {
        return casaCostruttrice;
    }

    public void setCasaCostruttrice(String casaCostruttrice) {
        this.casaCostruttrice = casaCostruttrice;
    }

    @XmlElement
    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    @XmlElement
    public TipoVeicolo getTipoVeicolo() {
        return tipoVeicolo;
    }

    public void setTipoVeicolo(TipoVeicolo tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
    }

    @XmlElement
    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(int annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    @XmlElement
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    @XmlElement
    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Veicolo{" +
                "id=" + id +
                ", casaCostruttrice='" + casaCostruttrice + '\'' +
                ", modello='" + modello + '\'' +
                ", tipoVeicolo=" + tipoVeicolo +
                ", annoImmatricolazione=" + annoImmatricolazione +
                ", targa='" + targa + '\'' +
                ", prenotazioni=" + prenotazioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veicolo veicolo = (Veicolo) o;
        return getAnnoImmatricolazione() == veicolo.getAnnoImmatricolazione() && Objects.equals(getId(), veicolo.getId()) && Objects.equals(getCasaCostruttrice(), veicolo.getCasaCostruttrice()) && Objects.equals(getModello(), veicolo.getModello()) && getTipoVeicolo() == veicolo.getTipoVeicolo() && Objects.equals(getTarga(), veicolo.getTarga()) && Objects.equals(getPrenotazioni(), veicolo.getPrenotazioni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCasaCostruttrice(), getModello(), getTipoVeicolo(), getAnnoImmatricolazione(), getTarga(), getPrenotazioni());
    }
}
