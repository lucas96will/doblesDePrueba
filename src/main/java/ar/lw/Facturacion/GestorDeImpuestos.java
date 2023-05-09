package ar.lw.Facturacion;

public class GestorDeImpuestos implements Impuesto {
    @Override
    public int aplicarImpuestos() {
        return 200;
    }
}
