package com.esempio.RentalCar.entities;

import com.esempio.RentalCar.validator.PeriodoPrenotazioneValidator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Embeddable
@Access(AccessType.FIELD)
public class PeriodoPrenotazione {

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataInizio")
    @NotNull(message = "{NotEmpty.Prenotazione.dataInizio.validator}")
    private Date dataInizio;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataFine")
    @NotNull(message = "{NotEmpty.Prenotazione.dataFine.validator}")
    private Date dataFine;

    public PeriodoPrenotazione() {
    }

    public PeriodoPrenotazione(Date dataInizio, Date dataFine) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "PeriodoPrenotazione{" +
                "dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}

