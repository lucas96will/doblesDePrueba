package ar.lw;

import ar.lw.Facturacion.Factura;
import ar.lw.Facturacion.Producto;
import ar.lw.Facturacion.Suministro;
import ar.lw.Facturacion.Linea;
import ar.lw.Facturacion.GestorDeImpuestos;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class FacturaTest {

    /*En este ejemplo, no hay falta de usar dobles, ya que lo que se esta probando es Factura
    * y no su colaboracion con GestorDeImpuestos*/
    @Test
    public void calcuarImpuestos() {

        // Arrange
        Suministro suministro = new Suministro();
        Producto producto = suministro.obtenerProductoConCodigo("123abc");

        Linea linea = new Linea();
        int cantidad = 10;
        linea.agregarProductos(producto, cantidad);

        Factura factura = new Factura(new GestorDeImpuestos());
        factura.agregarLinea(linea);

        // Act
        float total = factura.obtenerTotal();

        // Assert
        assertTrue(total >  cantidad * producto.precio() );

    }

    /*Este caso se usa un stub*/
    @Test
    public void calcuarImpuestosConGestorDeImpuestosImplementado() {

        // Arrange
        Suministro suministro = new Suministro();
        Producto producto = suministro.obtenerProductoConCodigo("123abc");

        Linea linea = new Linea();
        int cantidad = 10;
        linea.agregarProductos(producto, cantidad);

        Factura factura = new Factura(new GestorDeImpuestos());
        factura.agregarLinea(linea);

        // Act
        float total = factura.obtenerTotal();

        // Assert
        assertEquals(total, 200);

    }

    /*Uso de un mock*/
    @Test
    public void calcuarImpuestosConGestorDeImpuestosNoImplementado() {

        // Arrange
        Suministro suministro = new Suministro();
        Producto producto = suministro.obtenerProductoConCodigo("123abc");

        Linea linea = new Linea();
        int cantidad = 10;
        linea.agregarProductos(producto, cantidad);

        // Arrange - expectations - expectativas
        GestorDeImpuestos gestor = Mockito.mock(GestorDeImpuestos.class);
        when(gestor.aplicarImpuestos()).thenReturn(200);

        Factura factura = new Factura(gestor);
        factura.agregarLinea(linea);

        // Act
        float total = factura.obtenerTotal();

        // Assert
        assertEquals(total, 200);

    }
}
