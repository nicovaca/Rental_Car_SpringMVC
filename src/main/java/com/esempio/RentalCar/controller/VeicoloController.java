package com.esempio.RentalCar.controller;


import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;
import com.esempio.RentalCar.exception.IdVeicoloException;
import com.esempio.RentalCar.service.VeicoloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/veicolo")
public class VeicoloController {


    private final VeicoloService veicoloService;

    public VeicoloController(VeicoloService veicoloService) {
        this.veicoloService = veicoloService;
    }

    @GetMapping("/veicoli")
    public String getVeicoli(Model model) {
        List<Veicolo> veicoli = veicoloService.getListaVeicoli();

        model.addAttribute("Veicoli", veicoli);
        return "veicoli";

    }

    @GetMapping("/nuovoVeicolo")
    public String InsNuovoVeicolo(Model model) {
        Veicolo veicolo = new Veicolo();

        model.addAttribute("Veicolo", veicolo);
        return "insmodveicolo";
    }

    @PostMapping("/nuovoVeicolo")
    public String getInsNuovoVeicolo(@Valid @ModelAttribute("Veicolo") Veicolo veicolo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insmodveicolo";
        }
        veicoloService.saveVeicolo(veicolo);

        return "redirect:/veicolo/veicoli";

    }


    @GetMapping("/modificaVeicolo")
    public String modificaVeicolo(@RequestParam("id") Long id, Model model) {
        Veicolo veicolo = veicoloService.getVeicolo(id);

        if (veicolo == null) {
            throw new IdVeicoloException(id);
        }
        model.addAttribute("Veicolo", veicolo);

        return "insmodveicolo";
    }

    @PostMapping("/modificaVeicolo")
    public String getModificaVeicolo(@Valid @ModelAttribute("Veicolo") Veicolo veicolo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insmodveicolo";
        }

        veicoloService.updateVeicolo(veicolo);

        return "redirect:/veicolo/veicoli";
    }

    @GetMapping("/eliminaVeicolo")
    public String eliminaVeicolo(@RequestParam("id") Long id) {
        Veicolo veicolo = veicoloService.getVeicolo(id);
        veicoloService.deleteVeicolo(veicolo);
        return "redirect:/veicolo/veicoli";

    }

    @ExceptionHandler(IdVeicoloException.class)
    public ModelAndView handleError(HttpServletRequest request, IdVeicoloException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("codice", exception.getId());
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL() + request.getQueryString());
        modelAndView.setViewName("idVeicoloException");
        return modelAndView;
    }

    @GetMapping("/cercaVeicolo")
    public String cercaVeicolo(@RequestParam("filtro") String filtro, Model model) {
        List<Veicolo> veicoli = veicoloService.getVeicoloByFilter(filtro);
        model.addAttribute("Veicoli", veicoli);
        return "veicoli";
    }

}
