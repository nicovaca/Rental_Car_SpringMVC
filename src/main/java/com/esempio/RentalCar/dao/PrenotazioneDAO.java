package com.esempio.RentalCar.dao;

import com.esempio.RentalCar.entities.Prenotazione;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;

import java.util.List;

public interface PrenotazioneDAO {
    void savePrenotazione(Prenotazione p);
    void updatePrenotazione(Prenotazione p);
    void deletePrenotazione(Prenotazione p);
    Prenotazione getPrenotazione(Long id);
    List<Prenotazione> getListaPrenotazioni();
    List<Prenotazione> getPrenotazioniByUtente(Utente utente);
    List<Prenotazione> getPrenotazioneByFilter(String filtro);
    List<Prenotazione> getPrenotazioniCustomerByFilter(String filtro, Utente utente);
}
