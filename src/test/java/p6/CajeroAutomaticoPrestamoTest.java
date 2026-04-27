package p6;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CajeroAutomaticoPrestamoTest {

    private CajeroAutomatico unCajeroAProbar;
    private GestorPrestamosPersonales mock;

    @Test
    public void testSolicitarPrestamoPersonal_Caso1_Valido() {

        System.out.println("solicitarPrestamoPersonal: caso 1 préstamo válido");
        /*
         * CASO 1:
         * Cantidad válida: 3000 está entre 1500 y 5500.
         * Plazo válido: 18 está entre 12 y 24 meses.
         * El gestor concede permiso.
         *
         * Resultado esperado:
         * El préstamo se realiza y el método devuelve true.
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");

        mock = createMock(GestorPrestamosPersonales.class);

        expect(mock.tienePermiso(3000, 18, "2026-04-27")).andReturn(true);

        mock.hacerPrestamo(3000, 18);

        replay(mock);

        boolean result = unCajeroAProbar.solicitarPrestamoPersonal(
                3000, 18, "2026-04-27", mock);

        assertTrue(result);

        verify(mock);

        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 1");
    }

    @Test
    public void testSolicitarPrestamoPersonal_Caso2_CantidadInferior() {

        System.out.println("solicitarPrestamoPersonal: caso 2 cantidad inferior al mínimo");
        /*
         * CASO 2:
         * Cantidad no válida: 1000 es menor que 1500.
         * Plazo válido: 18 está entre 12 y 24 meses.
         *
         * Resultado esperado:
         * El gestor no concede permiso.
         * No se debe llamar a hacerPrestamo.
         * El método devuelve false.
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");

        mock = createMock(GestorPrestamosPersonales.class);

        expect(mock.tienePermiso(1000, 18, "2026-04-27")).andReturn(false);

        replay(mock);

        boolean result = unCajeroAProbar.solicitarPrestamoPersonal(
                1000, 18, "2026-04-27", mock);

        assertFalse(result);

        verify(mock);

        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 2");
    }

    @Test
    public void testSolicitarPrestamoPersonal_Caso3_CantidadSuperior() {

        System.out.println("solicitarPrestamoPersonal: caso 3 cantidad superior al máximo");
        /*
         * CASO 3:
         * Cantidad no válida: 6000 es mayor que 5500.
         * Plazo válido: 18 está entre 12 y 24 meses.
         *
         * Resultado esperado:
         * El gestor no concede permiso.
         * No se realiza el préstamo.
         * El método devuelve false.
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");

        mock = createMock(GestorPrestamosPersonales.class);

        expect(mock.tienePermiso(6000, 18, "2026-04-27")).andReturn(false);

        replay(mock);

        boolean result = unCajeroAProbar.solicitarPrestamoPersonal(
                6000, 18, "2026-04-27", mock);

        assertFalse(result);

        verify(mock);

        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 3");
    }

    @Test
    public void testSolicitarPrestamoPersonal_Caso4_PlazoInferior() {

        System.out.println("solicitarPrestamoPersonal: caso 4 plazo inferior al mínimo");
        /*
         * CASO 4:
         * Cantidad válida: 3000 está entre 1500 y 5500.
         * Plazo no válido: 10 es menor que 12 meses.
         *
         * Resultado esperado:
         * El gestor no concede permiso.
         * No se realiza el préstamo.
         * El método devuelve false.
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");

        mock = createMock(GestorPrestamosPersonales.class);

        expect(mock.tienePermiso(3000, 10, "2026-04-27")).andReturn(false);

        replay(mock);

        boolean result = unCajeroAProbar.solicitarPrestamoPersonal(
                3000, 10, "2026-04-27", mock);

        assertFalse(result);

        verify(mock);

        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 4");
    }

    @Test
    public void testSolicitarPrestamoPersonal_Caso5_PlazoSuperior() {

        System.out.println("solicitarPrestamoPersonal: caso 5 plazo superior al máximo");
        /*
         * CASO 5:
         * Cantidad válida: 3000 está entre 1500 y 5500.
         * Plazo no válido: 30 es mayor que 24 meses.
         *
         * Resultado esperado:
         * El gestor no concede permiso.
         * No se realiza el préstamo.
         * El método devuelve false.
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");

        mock = createMock(GestorPrestamosPersonales.class);

        expect(mock.tienePermiso(3000, 30, "2026-04-27")).andReturn(false);

        replay(mock);

        boolean result = unCajeroAProbar.solicitarPrestamoPersonal(
                3000, 30, "2026-04-27", mock);

        assertFalse(result);

        verify(mock);

        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 5");
    }

    @Test
    public void testSolicitarPrestamoPersonal_Caso6_PrestamoMismoAnio() {

        System.out.println("solicitarPrestamoPersonal: caso 6 préstamo ya concedido en el mismo año");
        /*
         * CASO 6:
         * Cantidad válida: 3000 está entre 1500 y 5500.
         * Plazo válido: 18 está entre 12 y 24 meses.
         * Pero el gestor no concede permiso porque ya existe
         * otro préstamo concedido en el mismo año.
         *
         * Resultado esperado:
         * No se realiza el préstamo.
         * El método devuelve false.
         */
        unCajeroAProbar = new CajeroAutomatico("1111111111");
        unCajeroAProbar.iniciarSesion("1234");

        mock = createMock(GestorPrestamosPersonales.class);

        expect(mock.tienePermiso(3000, 18, "2026-04-27")).andReturn(false);

        replay(mock);

        boolean result = unCajeroAProbar.solicitarPrestamoPersonal(
                3000, 18, "2026-04-27", mock);

        assertFalse(result);

        verify(mock);

        unCajeroAProbar.cerrarSesion();
        reset(mock);

        System.out.println("Fin del caso de prueba 6");
    }
}