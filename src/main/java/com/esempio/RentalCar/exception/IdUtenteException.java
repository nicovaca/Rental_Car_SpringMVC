package com.esempio.RentalCar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Utente non trovato")
public class IdUtenteException extends RuntimeException {
    private Long id;

    public IdUtenteException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
