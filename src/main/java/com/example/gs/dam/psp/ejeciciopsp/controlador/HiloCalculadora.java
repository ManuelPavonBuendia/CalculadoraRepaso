package com.example.gs.dam.psp.ejeciciopsp.controlador;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import com.example.gs.dam.psp.ejeciciopsp.modelo.Calculadora;
import com.example.gs.dam.psp.ejeciciopsp.util.cifrado.AESUtil;
import com.example.gs.dam.psp.ejeciciopsp.util.conexion.Common;
import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.NumeroInvalidoException;
import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.OperacionNoValidaException;
import com.example.gs.dam.psp.ejeciciopsp.util.hash.HashUtil;
import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogsUtil;

public class HiloCalculadora implements Runnable {

    private final Socket cliente;
    private final Calculadora calculadora = new Calculadora();
    private static final Logger logger = LogsUtil.getLogger(HiloCalculadora.class.getName());

    private static final String PASS_AES = "1234567890123456";
    private static final String SEPARADOR = ",";
    private static final String MENSAJE_ERROR_FORMATO = "ERROR FORMATO";
    private static final String MENSAJE_ERROR_HASH = "ERROR HASH";
    private static final String MENSAJE_ERROR_OPERACION = "ERROR OPERACION";
    private static final String MENSAJE_KO = "KO";
    private static final String MENSAJE_ERROR_IO = "Conexión cerrada o problema de I/O: %s";
    private static final String MENSAJE_NUMERO_INVALIDO = "Formato invalido";
    private static final int INDICE_NUMERO = 0;
    private static final int INDICE_HASH = 1;
    private static final int NUMERO_PARTES = 2;

    public HiloCalculadora(Socket cliente) {
        this.cliente = cliente;
    }

    private double parsearNumero(String entrada) throws NumeroInvalidoException {
        try {
            return Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            throw new NumeroInvalidoException(MENSAJE_NUMERO_INVALIDO);
        }
    }

    private double validarYExtraerNumero(String entrada) throws Exception {
        String[] partes = entrada.split(SEPARADOR);

        if (partes.length != NUMERO_PARTES) {
            throw new Exception(MENSAJE_ERROR_FORMATO);
        }

        String numeroStr = partes[INDICE_NUMERO];
        String hashRecibido = partes[INDICE_HASH];
        String hashCalculado = HashUtil.comprobarHash(numeroStr);

        if (!hashCalculado.equals(hashRecibido)) {
            throw new Exception(MENSAJE_ERROR_HASH);
        }

        return parsearNumero(numeroStr);
    }

    @Override
    public void run() {
        try {
            Common conexion = new Common(cliente);
            String entrada;

            while ((entrada = conexion.leer()) != null && !entrada.isBlank()) {
                try {
                    String mensaje = AESUtil.descifrar(entrada, PASS_AES);

                    double numero = validarYExtraerNumero(mensaje);
                    double resultado = calculadora.calcular(numero);

                    String resultadoSt = String.valueOf(resultado);
                    String resultadoCifrado = AESUtil.cifrar(resultadoSt, PASS_AES);

                    conexion.escribir(resultadoCifrado);

                } catch (NumeroInvalidoException e) {
                    conexion.escribir(MENSAJE_KO);

                } catch (OperacionNoValidaException e) {
                    conexion.escribir(MENSAJE_ERROR_OPERACION);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            logger.warning(() -> String.format(MENSAJE_ERROR_IO, e.getMessage()));
        }
    }
}
