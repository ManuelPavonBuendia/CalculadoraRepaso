package com.example.gs.dam.psp.ejeciciopsp.util.hash;

import java.security.MessageDigest;
import java.util.logging.Logger;

import com.example.gs.dam.psp.ejeciciopsp.util.logs.LogsUtil;

public class HashUtil {

    private static final Logger logger = LogsUtil.getLogger("Cliente");

    public static String convertirSHA256(String cadena) throws Exception {
        MessageDigest md = null;
        byte[] hash = null;
        md = MessageDigest.getInstance("SHA-256");
        hash = md.digest(cadena.getBytes("UTF-8"));
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String comprobarHash(String cadena) {
        try {
            return convertirSHA256(cadena);
        } catch (Exception e) {
            logger.warning(() -> "Error al generar hash: " + e.getMessage());
            return cadena;
        }
    }

}
