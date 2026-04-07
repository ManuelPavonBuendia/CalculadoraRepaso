package com.example.gs.dam.psp.ejeciciopsp.vista;

import java.util.Scanner;

public class VistaCliente {

    private static final String MENSAJE_PEDIR = "Introduce número: ";
    private static final String FORMATO_RESULTADO = "Resultado: %s";

    private final Scanner teclado = new Scanner(System.in);

    public String pedirNumero() {
        System.out.print(MENSAJE_PEDIR);
        return teclado.nextLine();
    }

    public void mostrarResultado(String resultado) {
        System.out.println(String.format(FORMATO_RESULTADO, resultado));
    }
}
