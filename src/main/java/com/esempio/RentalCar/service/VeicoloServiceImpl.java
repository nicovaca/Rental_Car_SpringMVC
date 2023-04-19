package com.esempio.RentalCar.service;

import com.esempio.RentalCar.dao.VeicoloDAO;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeicoloServiceImpl implements VeicoloService {

    final
    VeicoloDAO veicoloDAO;

    public VeicoloServiceImpl(VeicoloDAO veicoloDAO) {
        this.veicoloDAO = veicoloDAO;
    }

    @Override
    public void saveVeicolo(Veicolo v) {
        veicoloDAO.saveVeicolo(v);
    }

    @Override
    public void updateVeicolo(Veicolo v) {
        veicoloDAO.updateVeicolo(v);
    }

    @Override
    public void deleteVeicolo(Veicolo v) {
        veicoloDAO.deleteVeicolo(v);
    }

    @Override
    public Veicolo getVeicolo(Long id) {
        return veicoloDAO.getVeicolo(id);
    }

    @Override
    public List<Veicolo> getListaVeicoli() {
        return veicoloDAO.getListaVeicoli();
    }

    @Override
    public List<Veicolo> getVeicoloByFilter(String filtro) {
        return veicoloDAO.getVeicoloByFilter(filtro);
    }
}
