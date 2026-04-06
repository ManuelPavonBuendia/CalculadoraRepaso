package com.example.gs.dam.psp.ejeciciopsp.util.cifrado;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    public static String cifrar(String mensaje, String pass) throws Exception {
        Key key = new SecretKeySpec(pass.getBytes("UTF-8"), 0, 16, "AES");
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] cifrado = aes.doFinal(mensaje.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cifrado);
    }

    // Descifrar con AES
    public static String descifrar(String mensaje, String pass) throws Exception {
        Key key = new SecretKeySpec(pass.getBytes("UTF-8"), 0, 16, "AES");
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aes.init(Cipher.DECRYPT_MODE, key);
        byte[] cifrado = Base64.getDecoder().decode(mensaje);
        byte[] descifrado = aes.doFinal(cifrado);
        return new String(descifrado, "UTF-8");
    }
}
