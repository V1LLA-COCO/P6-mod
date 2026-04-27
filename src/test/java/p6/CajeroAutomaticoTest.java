package p6;

import java.util.Date;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CajeroAutomaticoTest {

    private CajeroAutomatico unCajeroAProbar;
    private PasarelaPago mock;

    @Test
    public void testRealizarRetirada1() {

        System.out.println("realizarRetirada: caso 1 no bloqueada y con saldo");

        /* (1) Se crea el objeto de la clase a probar y un mock para simular la clase PasarelaPago
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");
        mock = createMock(PasarelaPago.class);

        /* (2) En estado de "grabación", se le dice al objeto Simulado las llamadas que debe esperar y cómo responder a ellas.
         */
        expect(mock.estaBloqueada()).andReturn(false);
        expect(mock.tieneSaldo(500)).andReturn(true);
        mock.retirar(500);

        /* (3) Ahora, el objeto simulado comienza a esperar las llamadas
         */
        replay(mock);

        /* (4) Se programa la prueba del objto de la clase a probar
         */
        boolean result = unCajeroAProbar.realizarRetirada(500, mock);
        assertTrue(result);

        /* (5) Forzamos a que la ausencia de todas las llamadas previstas sea un error también
         */
        verify(mock);

        /* (6) Se ejecutan instrucciones necesarias de finalizaciónde la prueba
        /* y se resetea el mock
         */
        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 1");
    }

    @Test
    public void testRealizarRetirada2() {
        System.out.println("realizarRetirada: caso 2 no bloqueada y sin saldo");

        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");
        mock = createMock(PasarelaPago.class);

        expect(mock.estaBloqueada()).andReturn(false);
        expect(mock.tieneSaldo(500)).andReturn(false);
        mock.retirar(500);

        replay(mock);

        boolean result = unCajeroAProbar.realizarRetirada(500, mock);
        assertFalse(result);

        /* En este caso no se pone verify porque no es obligatorio ejecutar "retirar" */
        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 2");
    }

    @Test
    public void testRealizarRetirada3() {
        System.out.println("realizarRetirada: caso 3 con bloqueo y con saldo");

        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");
        mock = createMock(PasarelaPago.class);

        expect(mock.estaBloqueada()).andReturn(true);
        expect(mock.tieneSaldo(500)).andReturn(true);
        mock.retirar(500);

        replay(mock);

        boolean result = unCajeroAProbar.realizarRetirada(500, mock);
        assertFalse(result);

        /* En este caso no se pone verify porque no es obligatorio ejecutar "tieneSaldo" ni "retirar" */
        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 3");
    }

    @Test
    public void testRealizarRetirada4() {
        System.out.println("realizarRetirada: caso 4 con bloqueo y sin saldo");

        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");
        mock = createMock(PasarelaPago.class);

        expect(mock.estaBloqueada()).andReturn(true);
        expect(mock.tieneSaldo(500)).andReturn(false);
        mock.retirar(500);

        replay(mock);

        boolean result = unCajeroAProbar.realizarRetirada(500, mock);
        assertFalse(result);

        /* En este caso no se pone verify porque no es obligatorio ejecutar "tieneSaldo" ni "retirar" */
        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 4");
    }



}
