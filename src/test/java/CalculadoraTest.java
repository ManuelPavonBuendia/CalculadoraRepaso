
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.gs.dam.psp.ejeciciopsp.modelo.Calculadora;
import com.example.gs.dam.psp.ejeciciopsp.util.excepciones.OperacionNoValidaException;

public class CalculadoraTest {

    private final Calculadora calc = new Calculadora();

    private static final double NUMERO_POSITIVO = 5;
    private static final double RESULTADO_POSITIVO = 25.0;

    private static final double NUMERO_CERO = 0;
    private static final double RESULTADO_CERO = 0.0;

    private static final double NUMERO_NEGATIVO = -3;

    private static final String MENSAJE_ERROR_NEGATIVOS = "No se permiten negativos";

    @Test
    void testCalcularPositivo() throws OperacionNoValidaException {
        assertEquals(RESULTADO_POSITIVO, calc.calcular(NUMERO_POSITIVO));
    }

    @Test
    void testCalcularCero() throws OperacionNoValidaException {
        assertEquals(RESULTADO_CERO, calc.calcular(NUMERO_CERO));
    }

    @Test
    void testCalcularNegativo() {
        Exception exception = assertThrows(OperacionNoValidaException.class, () -> {
            calc.calcular(NUMERO_NEGATIVO);
        });

        assertEquals(MENSAJE_ERROR_NEGATIVOS, exception.getMessage());
    }
}
