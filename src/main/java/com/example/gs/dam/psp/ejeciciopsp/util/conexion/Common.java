package com.example.gs.dam.psp.ejeciciopsp.util.conexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Common {

    private final Socket cliente;
    private final BufferedReader lector;
    private final BufferedWriter escritor;

    public Common(Socket cliente) throws IOException {
        this.cliente = cliente;
        lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
    }

    public String leer() throws IOException {
        return lector.readLine();
    }

    public void escribir(String mensaje) throws IOException {
        escritor.write(mensaje);
        escritor.newLine();
        escritor.flush();
    }

}
