package com.example.gs.dam.psp.ejeciciopsp;

import java.net.Socket;
import java.util.logging.Logger;

import com.example.gs.dam.psp.ejeciciopsp.controlador.ClienteControlador;
import com.example.gs.dam.psp.ejeciciopsp.modelo.Calculadora;
import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.ClienteConexionException;
import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogsUtil;
import com.example.gs.dam.psp.ejeciciopsp.vista.VistaCliente;

public class ClienteArranque {

    private static final Logger logger = LogsUtil.getLogger(ClienteArranque.class.getName());
    public static final int PUERTO = 8888;
    public static final String HOST = "localhost";

    public static void main(String[] args) {

        VistaCliente vista = new VistaCliente();
        Calculadora modelo = new Calculadora();

        try (Socket clienteSocket = new Socket(HOST, PUERTO)) {
            ClienteControlador controlador = new ClienteControlador(clienteSocket, modelo, vista);
            controlador.iniciar();
        } catch (ClienteConexionException e) {
            logger.severe("Error en cliente: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Error inesperado: " + e.getMessage());
        }
    }
}
