package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean(name="ventaBean")
@SessionScoped
public class ventaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cantProducto;
    private double totalVenta;
    private String metodoEnvio;
    private String metodoPago;

    @ManagedProperty(value="#{carritoBean}")
    private CarritoBean carritoBean; // NO debe ser static

    public CarritoBean getCarritoBean() { return carritoBean; }
    public void setCarritoBean(CarritoBean carritoBean) { this.carritoBean = carritoBean; }

    public void inicializar() {
        if (carritoBean != null) {
            this.cantProducto = carritoBean.getCantidadProductos();
            this.totalVenta = carritoBean.getTotal();
        }
    }

    // getters y setters de los demás atributos...




    // Getters y setters
    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    public String getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(String metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String guardarVenta() {
        // Aquí va la lógica para guardar en BD
        System.out.println("Venta guardada:");
        System.out.println("Cantidad: " + cantProducto);
        System.out.println("Envío: " + metodoEnvio);
        System.out.println("Total: " + totalVenta);
        System.out.println("Pago: " + metodoPago);

        return "detalle-venta.xhtml?faces-redirect=true";
    }
}
