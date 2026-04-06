package com.example.gs.dam.psp.ejeciciopsp.modelo;

import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.OperacionNoValidaException;

public class Calculadora {

    public Double calcular(double n1) throws OperacionNoValidaException {
        if (n1 < 0) {
            throw new OperacionNoValidaException("No se permiten negativos");
        }
        return Math.pow(n1, 2);
    }
}
