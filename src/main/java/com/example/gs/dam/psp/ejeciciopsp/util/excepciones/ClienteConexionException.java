package com.example.gs.dam.psp.ejeciciopsp.util.excepciones;

public class ClienteConexionException extends ClienteException {

    public ClienteConexionException(String mensaje) {
        super(mensaje);
    }

    public ClienteConexionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
