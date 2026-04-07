package com.example.gs.dam.psp.ejeciciopsp.controlador;

import java.io.IOException;
import java.net.Socket;

import com.example.gs.dam.psp.ejeciciopsp.util.cifrado.AESUtil;
import com.example.gs.dam.psp.ejeciciopsp.util.conexion.Common;
import com.example.gs.dam.psp.ejeciciopsp.util.hash.HashUtil;
import com.example.gs.dam.psp.ejeciciopsp.vista.VistaCliente;

public class ClienteControlador {

    private static final int PUERTO = 8888;
    private static final String HOST = "localhost";
    private static final String FORMATO_MENSAJE = "%s,%s";
    private static final String PASS_AES = "1234567890123456";

    public void iniciar() throws Exception {
        String entrada;
        String entradaHash;
        String mensaje;
        String mensajeCifrado;
        String resultado;
        String resultadoDescifrado;

        try (Socket cliente = new Socket(HOST, PUERTO)) {
            Common common = new Common(cliente);
            VistaCliente vista = new VistaCliente();

            entrada = vista.pedirNumero();
            entradaHash = HashUtil.comprobarHash(entrada);
            mensaje = String.format(FORMATO_MENSAJE, entrada, entradaHash);
            mensajeCifrado = AESUtil.cifrar(mensaje, PASS_AES);
            common.escribir(mensajeCifrado);
            resultado = common.leer();
            resultadoDescifrado = AESUtil.descifrar(resultado, PASS_AES);
            vista.mostrarResultado(resultadoDescifrado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
