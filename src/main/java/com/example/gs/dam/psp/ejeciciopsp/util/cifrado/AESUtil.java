package com.example.gs.dam.psp.ejeciciopsp.util.cifrado;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    private static final String ALGORITMO = "AES";
    private static final String TRANSFORMACION = "AES/ECB/PKCS5Padding";
    private static final String CHARSET = "UTF-8";
    private static final int LONGITUD_CLAVE = 16;
    private static final int POSICION_INICIO = 0;
    private static final int MODO_ENCRIPTAR = Cipher.ENCRYPT_MODE;
    private static final int MODO_DESENCRIPTAR = Cipher.DECRYPT_MODE;

    public static String cifrar(String mensaje, String pass) throws Exception {

        Key key = new SecretKeySpec(pass.getBytes(CHARSET), POSICION_INICIO, LONGITUD_CLAVE, ALGORITMO);

        Cipher aes = Cipher.getInstance(TRANSFORMACION);
        aes.init(MODO_ENCRIPTAR, key);

        byte[] cifrado = aes.doFinal(mensaje.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(cifrado);
    }

    public static String descifrar(String mensaje, String pass) throws Exception {

        Key key = new SecretKeySpec(pass.getBytes(CHARSET), POSICION_INICIO, LONGITUD_CLAVE, ALGORITMO);

        Cipher aes = Cipher.getInstance(TRANSFORMACION);
        aes.init(MODO_DESENCRIPTAR, key);

        byte[] cifrado = Base64.getDecoder().decode(mensaje);
        byte[] descifrado = aes.doFinal(cifrado);

        return new String(descifrado, CHARSET);
    }
}
