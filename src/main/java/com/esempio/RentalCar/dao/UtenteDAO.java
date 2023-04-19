package com.esempio.RentalCar.dao;

import com.esempio.RentalCar.entities.Utente;

import java.util.List;

public interface UtenteDAO {

    void saveUtente(Utente u);
    void updateUtente(Utente u);
    void deleteUtente(Utente u);
    Utente getUtente(Long id);
    List<Utente> getListaUtenti();
    List<Utente> getUtenteByFilter(String filtro);
    Utente getUtenteByUsername(String username);
}
