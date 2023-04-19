package com.esempio.RentalCar.entities;

import com.esempio.RentalCar.validator.PeriodoPrenotazioneValidator;
import org.codehaus.jackson.annotate.JsonIgnore;


import javax.persistence.*;
import javax.validation.Valid;
import java.util.Objects;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    @Valid
    @PeriodoPrenotazioneValidator
    private PeriodoPrenotazione periodoPrenotazione;

    @Column(name = "approvazione")
    private boolean approvazione;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUtente", referencedColumnName = "id")
    private Utente utente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idVeicolo", referencedColumnName = "id")
    private Veicolo veicolo;


    public Prenotazione() {
    }

    public Prenotazione(PeriodoPrenotazione periodoPrenotazione, boolean approvazione, Utente utente, Veicolo veicolo) {
        this.periodoPrenotazione = periodoPrenotazione;
        this.approvazione = approvazione;
        this.utente = utente;
        this.veicolo = veicolo;
    }

    public Prenotazione(Long id, PeriodoPrenotazione periodoPrenotazione, boolean approvazione, Utente utente, Veicolo veicolo) {
        this.id = id;
        this.periodoPrenotazione = periodoPrenotazione;
        this.approvazione = approvazione;
        this.utente = utente;
        this.veicolo = veicolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeriodoPrenotazione getPeriodoPrenotazione() {
        return periodoPrenotazione;
    }

    public void setPeriodoPrenotazione(PeriodoPrenotazione periodoPrenotazione) {
        this.periodoPrenotazione = periodoPrenotazione;
    }

    public boolean isApprovazione() {
        return approvazione;
    }

    public void setApprovazione(boolean approvazione) {
        this.approvazione = approvazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", periodoPrenotazione=" + periodoPrenotazione +
                ", approvazione=" + approvazione +
                ", utente=" + utente +
                ", veicolo=" + veicolo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return isApprovazione() == that.isApprovazione() && Objects.equals(getId(), that.getId()) && Objects.equals(getPeriodoPrenotazione(), that.getPeriodoPrenotazione()) && Objects.equals(getUtente(), that.getUtente()) && Objects.equals(getVeicolo(), that.getVeicolo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPeriodoPrenotazione(), isApprovazione(), getUtente(), getVeicolo());
    }
}
