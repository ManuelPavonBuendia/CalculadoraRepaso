package com.example.gs.dam.psp.ejeciciopsp.util.conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Common {

    private final DataInputStream in;
    private final DataOutputStream out;

    public Common(Socket cliente) throws IOException {
        in = new DataInputStream(cliente.getInputStream());
        out = new DataOutputStream(cliente.getOutputStream());
    }

    public String leer() throws IOException {
        return in.readUTF();
    }

    public void escribir(String mensaje) throws IOException {
        out.writeUTF(mensaje);
        out.flush();
    }
}
