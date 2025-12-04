
package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

@ManagedBean(name="productoBean")
@SessionScoped
public class ProductoBean implements Serializable {
    private List<Producto> listaProd;

    public ProductoBean() {
        listaProd = new ArrayList<>();

        Producto p1 = new Producto();
        p1.setIdProducto(1);
        p1.setNomProducto("Camiseta Deportiva");
        p1.setTallaProducto("M");
        p1.setPrecioProducto(59900);
        p1.setCategoria("Ropa");
        listaProd.add(p1);

        Producto p2 = new Producto();
        p2.setIdProducto(2);
        p2.setNomProducto("Zapatos Running");
        p2.setTallaProducto("42");
        p2.setPrecioProducto(199900);
        p2.setCategoria("Calzado");
        listaProd.add(p2);
    }

    public List<Producto> getListaProd() { return listaProd; }
    public void setListaProd(List<Producto> listaProd) { this.listaProd = listaProd; }
}

