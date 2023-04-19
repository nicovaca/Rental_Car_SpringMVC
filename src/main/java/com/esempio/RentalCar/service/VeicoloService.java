package com.esempio.RentalCar.service;

import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;

import java.util.List;

public interface VeicoloService {
    void saveVeicolo(Veicolo v);
    void updateVeicolo(Veicolo v);
    void deleteVeicolo(Veicolo v);
    Veicolo getVeicolo(Long id);
    List<Veicolo> getListaVeicoli();
    List<Veicolo> getVeicoloByFilter(String filtro);
}
