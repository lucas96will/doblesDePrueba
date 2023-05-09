package ar.lw.Facturacion;

public class Factura {

    private GestorDeImpuestos gestor;

    public Factura(GestorDeImpuestos gestorDeImpuestos) {
        gestor = gestorDeImpuestos;
    }

    public void agregarLinea(Linea linea) {
    }

    public float obtenerTotal() {
        return gestor.aplicarImpuestos();
    }
}
