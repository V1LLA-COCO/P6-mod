package p6;

/**
 * Interfaz que simula el gestor de préstamos personales.
 */
public interface GestorPrestamosPersonales {

    /**
     * Comprueba si el cliente tiene permiso para solicitar un préstamo personal.
     *
     * @param cantidad cantidad solicitada del préstamo
     * @param plazoMeses plazo de amortización en meses
     * @param fechaSolicitud fecha de solicitud del préstamo
     * @return true si se concede permiso, false en caso contrario
     */
    public boolean tienePermiso(double cantidad, int plazoMeses, String fechaSolicitud);

    /**
     * Realiza el préstamo personal solicitado.
     *
     * @param cantidad cantidad solicitada del préstamo
     * @param plazoMeses plazo de amortización en meses
     */
    public void hacerPrestamo(double cantidad, int plazoMeses);
}