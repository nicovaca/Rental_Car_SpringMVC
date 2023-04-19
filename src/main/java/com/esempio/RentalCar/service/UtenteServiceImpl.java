package com.esempio.RentalCar.service;

import com.esempio.RentalCar.dao.UtenteDAO;
import com.esempio.RentalCar.entities.Utente;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

    final
    UtenteDAO utenteDAO;

    public UtenteServiceImpl(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @Override
    public void saveUtente(Utente u) {
        utenteDAO.saveUtente(u);
    }

    @Override
    public void updateUtente(Utente u) {
        utenteDAO.updateUtente(u);
    }

    @Override
    public void deleteUtente(Utente u) {
        utenteDAO.deleteUtente(u);
    }

    @Override
    public Utente getUtente(Long id) {
        return utenteDAO.getUtente(id);
    }

    @Override
    public List<Utente> getListaUtenti() {
        return utenteDAO.getListaUtenti();
    }

    @Override
    public List<Utente> getUtenteByFilter(String filtro) {
        return utenteDAO.getUtenteByFilter(filtro);
    }

    @Override
    public Utente getUtenteByUsername(String username) {
        return utenteDAO.getUtenteByUsername(username);
    }
}
