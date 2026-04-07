package com.example.gs.dam.psp.ejeciciopsp.arranque;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import com.example.gs.dam.psp.ejeciciopsp.controlador.HiloCalculadora;
import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogMessages;
import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogsUtil;

public class Servidor {

    static final int PUERTO = 8888;
    private static final Logger logger = LogsUtil.getLogger(HiloCalculadora.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PUERTO)) {
            logger.info(String.format(LogMessages.SERVIDOR_INICIADO, PUERTO));
            while (true) {
                Socket cliente = server.accept();
                new Thread(new HiloCalculadora(cliente)).start();
            }
        }
    }
}
