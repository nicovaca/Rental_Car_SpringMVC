package com.esempio.RentalCar.service;

import com.esempio.RentalCar.entities.Utente;

import java.util.List;

public interface UtenteService {
    void saveUtente(Utente u);
    void updateUtente(Utente u);
    void deleteUtente(Utente u);
    Utente getUtente(Long id);
    List<Utente> getListaUtenti();
    List<Utente> getUtenteByFilter(String filtro);
    Utente getUtenteByUsername(String username);
}
