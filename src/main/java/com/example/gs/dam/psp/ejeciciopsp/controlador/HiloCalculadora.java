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

    public HiloCalculadora(Socket cliente) {
        this.cliente = cliente;
    }

    private double parsearNumero(String entrada) throws NumeroInvalidoException {
        try {
            return Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            throw new NumeroInvalidoException("Formato invalido");
        }
    }

    private double validarYExtraerNumero(String entrada) throws Exception {
        String[] partes = entrada.split(",");
        if (partes.length != 2) {
            throw new Exception("ERROR FORMATO");
        }
        String numeroStr = partes[0];
        String hashRecibido = partes[1];
        String hashCalculado = HashUtil.comprobarHash(numeroStr);
        if (!hashCalculado.equals(hashRecibido)) {
            throw new Exception("ERROR HASH");
        }
        return parsearNumero(numeroStr);
    }

    @Override
    public void run() {
        try {
            Common conexion = new Common(cliente);
            String entrada;
            String pass = "1234567890123456";
            while ((entrada = conexion.leer()) != null && !entrada.isBlank()) {
                try {
                    String mensaje = AESUtil.descifrar(entrada, pass);
                    double numero = validarYExtraerNumero(mensaje);
                    double resultado = calculadora.calcular(numero);
                    String resultadoSt = String.valueOf(resultado);
                    String resultadoCifrado = AESUtil.cifrar(resultadoSt, pass);
                    conexion.escribir(resultadoCifrado);
                } catch (NumeroInvalidoException e) {
                    conexion.escribir("KO");
                } catch (OperacionNoValidaException e) {
                    conexion.escribir("ERROR OPERACION");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            logger.warning(() -> "Conexión cerrada o problema de I/O: " + e.getMessage());
        }
    }
}
