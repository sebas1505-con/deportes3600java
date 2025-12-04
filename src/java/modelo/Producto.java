
package modelo;


public class Producto {
    private int idProducto;
    private String nomProducto;
    private String tallaProducto;
    private double precioProducto;
    private String categoria;

    // Getters y setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNomProducto() { return nomProducto; }
    public void setNomProducto(String nomProducto) { this.nomProducto = nomProducto; }

    public String getTallaProducto() { return tallaProducto; }
    public void setTallaProducto(String tallaProducto) { this.tallaProducto = tallaProducto; }

    public double getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(double precioProducto) { this.precioProducto = precioProducto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
