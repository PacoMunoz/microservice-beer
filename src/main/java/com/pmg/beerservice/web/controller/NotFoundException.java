package com.pmg.beerservice.web.controller;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("No se ha encontrado ningun elemento a devolver.");
    }
}
