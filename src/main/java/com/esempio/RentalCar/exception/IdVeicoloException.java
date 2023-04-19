package com.esempio.RentalCar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Veicolo non presente")
public class IdVeicoloException extends RuntimeException {

    private Long id;

    public IdVeicoloException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
