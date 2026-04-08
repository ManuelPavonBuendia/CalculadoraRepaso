
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import com.example.gs.dam.psp.ejeciciopsp.util.hash.HashUtil;

public class HashUtilTest {

    private static final String TEXTO_PRUEBA = "Prueba";
    private static final String TEXTO_PRUEBA_1 = "Prueba1";
    private static final String TEXTO_PRUEBA_2 = "Prueba2";

    private static final String ASSERT_HASH_IGUAL = "El hash debe ser consistente para el mismo texto";
    private static final String ASSERT_HASH_DIFERENTE = "Hashes de textos diferentes deben diferir";

    @Test
    void testHashConsistente() {
        String hash1 = HashUtil.comprobarHash(TEXTO_PRUEBA);
        String hash2 = HashUtil.comprobarHash(TEXTO_PRUEBA);

        assertEquals(hash1, hash2, ASSERT_HASH_IGUAL);
    }

    @Test
    void testHashDiferente() {
        String hash1 = HashUtil.comprobarHash(TEXTO_PRUEBA_1);
        String hash2 = HashUtil.comprobarHash(TEXTO_PRUEBA_2);

        assertNotEquals(hash1, hash2, ASSERT_HASH_DIFERENTE);
    }
}
