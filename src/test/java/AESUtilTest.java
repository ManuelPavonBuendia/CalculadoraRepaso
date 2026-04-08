
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import com.example.gs.dam.psp.ejeciciopsp.util.cifrado.AESUtil;

public class AESUtilTest {

    private static final String PASS = "1234567890123456";
    private static final String MENSAJE_PRUEBA = "Mensaje secreto";

    private static final String ASSERT_DESCIFRADO = "El mensaje descifrado debe coincidir con el original";
    private static final String ASSERT_CIFRADO_DIFERENTE = "El mensaje cifrado no debe ser igual al original";

    @Test
    void testCifradoCompleto() throws Exception {

        String cifrado = AESUtil.cifrar(MENSAJE_PRUEBA, PASS);
        String descifrado = AESUtil.descifrar(cifrado, PASS);

        assertAll(
                () -> assertEquals(MENSAJE_PRUEBA, descifrado, ASSERT_DESCIFRADO),
                () -> assertNotEquals(MENSAJE_PRUEBA, cifrado, ASSERT_CIFRADO_DIFERENTE)
        );
    }
}
