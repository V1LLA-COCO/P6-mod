package p6;

import java.util.Date;

/**
 * Simula un cajero automático.
 */
public class CajeroAutomatico {

    private String _codigo = null;

    /**
     * La cuenta corriente sobre la que se opera en una sesión.
     */
    private String _cuenta;

    public CajeroAutomatico(String codigo) {
        _codigo = codigo;
    }

    public void iniciarSesion(String ccc) {
        assert (_cuenta == null);
        _cuenta = ccc;
    }

    public void cerrarSesion() {
        assert (_cuenta != null);
        _cuenta = null;
    }

    public boolean realizarRetirada(double cantidad, PasarelaPago p) {
        assert (_cuenta != null);
        if (p.estaBloqueada()) {
            return false;
        }
        if (p.tieneSaldo(cantidad)) {
            p.retirar(cantidad);
            return true;
        }
        return false;
    }
    /**
     * Método solicitarPrestamoPersonal() que se pide en el ejercicio 2.2.
     */
    public boolean solicitarPrestamoPersonal(double cantidad, int plazoMeses,
        String fechaSolicitud, GestorPrestamosPersonales gestor) {

    assert (_cuenta != null);

    if (gestor.tienePermiso(cantidad, plazoMeses, fechaSolicitud)) {
        gestor.hacerPrestamo(cantidad, plazoMeses);
        return true;
    }

    return false;
}
}
