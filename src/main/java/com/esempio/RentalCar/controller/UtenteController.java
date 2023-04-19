package com.esempio.RentalCar.controller;

import com.esempio.RentalCar.entities.Prenotazione;
import com.esempio.RentalCar.entities.Ruolo;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.exception.IdUtenteException;
import com.esempio.RentalCar.service.PrenotazioneService;
import com.esempio.RentalCar.service.UtenteServiceImpl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/utente")
public class UtenteController {

    private final
    UtenteServiceImpl utenteService;
    private final PrenotazioneService prenotazioneService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UtenteController(UtenteServiceImpl utenteService, PrenotazioneService prenotazioneService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/profiloAdmin")
    public String getUtenti(Model model) {
        List<Utente> utenti = utenteService.getListaUtenti();
        model.addAttribute("Utenti", utenti);
        return "profiloAdmin";
    }

    @GetMapping("/profiloCustomer")
    public String getCustomer(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente utente = utenteService.getUtenteByUsername(username);
        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByUtente(utente);
        model.addAttribute("Utente", utente);
        model.addAttribute("Prenotazioni", prenotazioni);
        return "profiloCustomer";
    }

    @GetMapping("/nuovoUtente")
    public String InsUtente(Model model) {
        Utente utente = new Utente();
        model.addAttribute("Utente", utente);
        return "insmodUtente";
    }

    @PostMapping("/nuovoUtente")
    public String getInsUtente(@Valid @ModelAttribute("Utente") Utente utente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insmodUtente";
        }
        //password cript
        utente.setPassword(bCryptPasswordEncoder.encode(utente.getPassword()));
        utenteService.saveUtente(utente);
        return "redirect:/utente/profiloAdmin";

    }

    @GetMapping("/eliminaUtente")
    public String eliminaUtente(@RequestParam("id") Long id) {
        Utente u = utenteService.getUtente(id);
        utenteService.deleteUtente(u);
        return "redirect:/utente/profiloAdmin";

    }

    @GetMapping("/modificaUtente")
    public String modificaUtente(@RequestParam("id") Long id, Model model) {
        Utente u = utenteService.getUtente(id);
        if (u == null) {
            throw new IdUtenteException(id);
        }
        u.setPassword("");
        model.addAttribute("Utente", u);

        return "insmodUtente";
    }

    @PostMapping("/modificaUtente")
    public String getModificaUtente(@Valid @ModelAttribute("Utente") Utente utente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insmodUtente";
        }
        //password cript
        utente.setPassword(bCryptPasswordEncoder.encode(utente.getPassword()));
        utenteService.updateUtente(utente);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente u = utenteService.getUtenteByUsername(username);

        if (u.getRuolo().equals(Ruolo.SUPERUSER)) {
            return "redirect: ../utente/profiloAdmin";
        } else {
            return "redirect: ../utente/profiloCustomer";
        }
    }

    @ExceptionHandler(IdUtenteException.class)
    public ModelAndView handleError(HttpServletRequest request, IdUtenteException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("codice", exception.getId());
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL() + request.getQueryString());
        modelAndView.setViewName("idUtenteException");
        return modelAndView;
    }

    @GetMapping("/registrazione")
    public String registraUtente(Model model) {
        Utente utente = new Utente();
        model.addAttribute("Utente", utente);
        return "registrazioneUtente";
    }

    @PostMapping("/registrazione")
    public String getRegistraUtente(@Valid @ModelAttribute("Utente") Utente utente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrazioneUtente";
        }
        utente.setPassword(bCryptPasswordEncoder.encode(utente.getPassword()));
        utenteService.saveUtente(utente);
        return "login";
    }

    //filtro utenti
    @GetMapping("/cercaUtente")
    public String cercaUtente(@RequestParam("filtro") String filtro, Model model) {

        List<Utente> utenti = utenteService.getUtenteByFilter(filtro);
        model.addAttribute("Utenti", utenti);
        return "profiloAdmin";

    }

    @GetMapping("/profilo")
    public String getProfilo(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == "anonymousUser") {
            return "redirect: ../login/form";
        } else {
            Utente utente = utenteService.getUtenteByUsername(username);
            if (utente.getRuolo().equals(Ruolo.SUPERUSER)) {
                return "redirect: ../utente/profiloAdmin";
            } else {
                return "redirect: ../utente/profiloCustomer";
            }
        }
    }

    @GetMapping("/dettagliUtente")
    public String dettagliUtente(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Utente u = utenteService.getUtenteByUsername(username);
        if (u == null) {
            throw new IdUtenteException(u.getId());
        }
        model.addAttribute("Utente", u);

        return "dettagliCustomer";
    }

}
