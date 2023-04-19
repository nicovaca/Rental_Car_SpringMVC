package com.esempio.RentalCar.service;

import com.esempio.RentalCar.entities.Ruolo;
import com.esempio.RentalCar.entities.Utente;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("customUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UtenteService utenteService;

    public CustomUserDetailsService(UtenteService utenteService) {
        this.utenteService = utenteService;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Inserisci l'username");
        }
        Utente utente = utenteService.getUtenteByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        User.UserBuilder userBuilder = null;
        userBuilder = User.withUsername(utente.getUsername());
        userBuilder.password(utente.getPassword());

        if (utente.getRuolo().equals(Ruolo.SUPERUSER)) {
            userBuilder.roles("SUPERUSER");
        } else {
            userBuilder.roles("CUSTOMER");
        }
        return userBuilder.build();
    }
}
