package com.example.gs.dam.psp.ejeciciopsp.util.excepciones;

public class ClienteException extends Exception {

    public ClienteException(String mensaje) {
        super(mensaje);
    }

    public ClienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
