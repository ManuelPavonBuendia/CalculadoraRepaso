package com.example.gs.dam.psp.ejeciciopsp.util.hash;

import java.security.MessageDigest;
import java.util.logging.Logger;

import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogMessages;
import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogsUtil;

public class HashUtil {

    // Constantes
    private static final String LOGGER_NAME = "Cliente";
    private static final String ALGORITMO_SHA = "SHA-256";
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final int FACTOR_LONG_HEX = 2;
    private static final int INDICE_INICIO = 0;
    private static final int LONGITUD_BYTE = 1;
    private static final int MASK_BYTE = 0xff;
    private static final char CHAR_PAD_HEX = '0';

    private static final Logger logger = LogsUtil.getLogger(LOGGER_NAME);

    public static String convertirSHA256(String cadena) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORITMO_SHA);
        byte[] hash = md.digest(cadena.getBytes(CHARSET_UTF8));
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(FACTOR_LONG_HEX * hash.length);
        for (int i = INDICE_INICIO; i < hash.length; i++) {
            String hex = Integer.toHexString(MASK_BYTE & hash[i]);
            if (hex.length() == LONGITUD_BYTE) {
                hexString.append(CHAR_PAD_HEX);
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String comprobarHash(String cadena) {
        try {
            return convertirSHA256(cadena);
        } catch (Exception e) {
            logger.warning(() -> String.format(LogMessages.ERROR_GENERAR_HASH, e.getMessage()));
            return cadena;
        }
    }
}
