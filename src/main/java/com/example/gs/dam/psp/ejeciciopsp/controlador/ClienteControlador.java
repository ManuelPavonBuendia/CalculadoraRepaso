package com.example.gs.dam.psp.ejeciciopsp.controlador;

import java.net.Socket;
import java.util.logging.Logger;

import com.example.gs.dam.psp.ejeciciopsp.modelo.Calculadora;
import com.example.gs.dam.psp.ejeciciopsp.util.cifrado.AESUtil;
import com.example.gs.dam.psp.ejeciciopsp.util.conexion.Common;
import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.ClienteConexionException;
import com.example.gs.dam.psp.ejeciciopsp.util.hash.HashUtil;
import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogsUtil;
import com.example.gs.dam.psp.ejeciciopsp.vista.VistaCliente;

public class ClienteControlador {

    private final Calculadora modelo;
    private final VistaCliente vista;
    private final Socket socket;

    private static final String FORMATO_MENSAJE = "%s,%s";
    private static final String PASS_AES = "1234567890123456";

    private static final Logger logger = LogsUtil.getLogger(ClienteControlador.class.getName());

    public ClienteControlador(Socket socket, Calculadora modelo, VistaCliente vista) {
        this.socket = socket;
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() throws ClienteConexionException {
        try {
            Common common = new Common(socket);

            String entrada = vista.pedirNumero();
            String entradaHash = HashUtil.comprobarHash(entrada);

            String mensaje = String.format(FORMATO_MENSAJE, entrada, entradaHash);
            String mensajeCifrado = AESUtil.cifrar(mensaje, PASS_AES);

            common.escribir(mensajeCifrado);
            String resultadoCifrado = common.leer();
            String resultado = AESUtil.descifrar(resultadoCifrado, PASS_AES);

            vista.mostrarResultado(resultado);

        } catch (Exception e) {
            // Logging centralizado
            logger.severe("Error en ClienteControlador: " + e.getMessage());
            throw new ClienteConexionException("Error durante la comunicación con el servidor", e);
        }
    }
}
