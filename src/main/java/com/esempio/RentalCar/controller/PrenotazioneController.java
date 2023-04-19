package com.esempio.RentalCar.controller;

import com.esempio.RentalCar.entities.Prenotazione;
import com.esempio.RentalCar.entities.Ruolo;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;
import com.esempio.RentalCar.exception.IdUtenteException;
import com.esempio.RentalCar.service.PrenotazioneService;
import com.esempio.RentalCar.service.UtenteService;
import com.esempio.RentalCar.service.VeicoloService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;
    private final VeicoloService veicoloService;

    private final UtenteService utenteService;

    public PrenotazioneController(PrenotazioneService prenotazioneService, VeicoloService veicoloService, UtenteService utenteService) {
        this.prenotazioneService = prenotazioneService;
        this.veicoloService = veicoloService;
        this.utenteService = utenteService;
    }


    @GetMapping("/listaPrenotazioni")
    public String getAllPrenotazioni(@RequestParam("id") Long id, Model model) {
        if (id == null) {

            List<Prenotazione> prenotazioni = prenotazioneService.getListaPrenotazioni();
            model.addAttribute("Prenotazioni", prenotazioni);
        } else {
            Utente utente = utenteService.getUtente(id);

            List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByUtente(utente);
            model.addAttribute("Prenotazioni", prenotazioni);


        }

        return "allPrenotazioni";
    }

    @GetMapping("/nuovaPrenotazione")
    public String insPrenotazione(Model model) {
        Prenotazione prenotazione = new Prenotazione();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteService.getUtenteByUsername(username);
        model.addAttribute("Prenotazione", prenotazione);
        model.addAttribute("Veicoli", getVeicoli());
        model.addAttribute("Utente", utente);
        return "insmodPrenotazione";
    }

    @ModelAttribute("Veicoli")
    public List<Veicolo> getVeicoli() {
        List<Veicolo> veicoli = veicoloService.getListaVeicoli();
        return veicoli;
    }

    @PostMapping("/nuovaPrenotazione")
    public String getInsPrenotazione(@Valid @ModelAttribute("Prenotazione") Prenotazione prenotazione, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insmodPrenotazione";
        }
        prenotazioneService.savePrenotazione(prenotazione);
        return "redirect:/utente/profiloCustomer";
    }

    @PostMapping("/gestisciPrenotazione")
    public String gestisciPrenotazione(@RequestParam("approva") String approva, @RequestParam("id") Long id) {

        Prenotazione prenotazione = prenotazioneService.getPrenotazione(id);

        if (approva.equals("true")) {
            prenotazioneService.approvaPrenotazione(prenotazione);
        }
        if (approva.equals("false")) {
            prenotazioneService.disapprovaPrenotazione(prenotazione);
        }

        return "redirect: listaPrenotazioni?id=";

    }

    @GetMapping("/modificaPrenotazione")
    public String modificaPrenotazione(@RequestParam("id") Long id, Model model) {
        Prenotazione p = prenotazioneService.getPrenotazione(id);
        model.addAttribute("Prenotazione", p);
        return "insmodPrenotazione";
    }

    @PostMapping("/modificaPrenotazione")
    public String getModificaPrenotazione(@Valid @ModelAttribute("Prenotazione") Prenotazione prenotazione, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insmodPrenotazione";
        }
        prenotazioneService.updatePrenotazione(prenotazione);
        return "redirect:/utente/profiloCustomer";
    }

    @GetMapping("/eliminaPrenotazione")
    public String eliminaPrenotazione(@RequestParam("id") Long id) {
        Date data = new Date();
        Prenotazione p = prenotazioneService.getPrenotazione(id);
        long diffInMillies = Math.abs(p.getPeriodoPrenotazione().getDataInizio().getTime() - data.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente u = utenteService.getUtenteByUsername(username);
        if (diff < 2 && u.getRuolo().equals(Ruolo.CUSTOMER)) {
            return "prenotazioniErrorDate";
        } else {
            prenotazioneService.deletePrenotazione(p);
        }

        if (u.getRuolo().equals(Ruolo.SUPERUSER)) {
            return "redirect: listaPrenotazioni?id=";
        } else {
            return "redirect: ../utente/profiloCustomer";
        }
    }

    //filtro Prenotazione Admin
    @GetMapping("/cercaPrenotazioni")
    public String cercaPrenotazioni(@RequestParam("filtro") String filtro, Model model) {

        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioneByFilter(filtro);
        model.addAttribute("Prenotazioni", prenotazioni);
        return "allPrenotazioni";

    }

    //filtro Prenotazioni Customer
    @GetMapping("/cercaPrenotazioniCustomer")
    public String cercaPrenotazioniCustomer(@RequestParam("filtro") String filtro, Model model, HttpServletRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteService.getUtenteByUsername(username);

        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniCustomerByFilter(filtro,utente);
        model.addAttribute("Prenotazioni", prenotazioni);

        request.setAttribute("Utente", utente);
        return "profiloCustomer";

    }
}
