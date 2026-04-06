package com.example.gs.dam.psp.ejeciciopsp.vista;

import java.util.Scanner;

public class VistaCliente {

    private final Scanner teclado = new Scanner(System.in);

    public String pedirNumero() {
        System.out.print("Introduce número: ");
        return teclado.nextLine();
    }

    public void mostrarResultado(String resultado) {
        System.out.println("Resultado: " + resultado);
    }
}
