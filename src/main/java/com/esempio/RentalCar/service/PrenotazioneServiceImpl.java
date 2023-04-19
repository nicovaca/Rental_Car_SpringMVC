package com.esempio.RentalCar.service;

import com.esempio.RentalCar.dao.PrenotazioneDAO;
import com.esempio.RentalCar.entities.Prenotazione;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService {

    final
    PrenotazioneDAO prenotazioneDAO;

    public PrenotazioneServiceImpl(PrenotazioneDAO prenotazioneDAO) {
        this.prenotazioneDAO = prenotazioneDAO;
    }

    @Override
    public void savePrenotazione(Prenotazione p) {
        prenotazioneDAO.savePrenotazione(p);
    }

    @Override
    public void updatePrenotazione(Prenotazione p) {
        prenotazioneDAO.updatePrenotazione(p);
    }

    @Override
    public void deletePrenotazione(Prenotazione p) {
        prenotazioneDAO.deletePrenotazione(p);
    }

    @Override
    public Prenotazione getPrenotazione(Long id) {
        return prenotazioneDAO.getPrenotazione(id);
    }

    @Override
    public List<Prenotazione> getListaPrenotazioni() {
        return prenotazioneDAO.getListaPrenotazioni();
    }

    @Override
    public List<Prenotazione> getPrenotazioniByUtente(Utente utente) {
        return prenotazioneDAO.getPrenotazioniByUtente(utente);
    }

    @Override
    public void approvaPrenotazione(Prenotazione p) {
        p.setApprovazione(true);
        prenotazioneDAO.updatePrenotazione(p);
    }

    @Override
    public void disapprovaPrenotazione(Prenotazione p) {
        p.setApprovazione(false);
        prenotazioneDAO.updatePrenotazione(p);
    }

    @Override
    public List<Prenotazione> getPrenotazioneByFilter(String filtro) {
        return prenotazioneDAO.getPrenotazioneByFilter(filtro);
    }

    @Override
    public List<Prenotazione> getPrenotazioniCustomerByFilter(String filtro, Utente utente) {
        return prenotazioneDAO.getPrenotazioniCustomerByFilter(filtro,utente);
    }
}
