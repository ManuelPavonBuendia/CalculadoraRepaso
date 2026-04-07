package com.example.gs.dam.psp.ejeciciopsp.modelo;

import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.OperacionNoValidaException;

public class Calculadora {

    // Constantes
    private static final String ERROR_NEGATIVOS = "No se permiten negativos";
    private static final double NUMERO_NEGATIVO = 0;
    private static final double EXPONENTE = 2;

    public Double calcular(double n1) throws OperacionNoValidaException {
        if (n1 < NUMERO_NEGATIVO) {
            throw new OperacionNoValidaException(ERROR_NEGATIVOS);
        }
        return Math.pow(n1, EXPONENTE);
    }
}
