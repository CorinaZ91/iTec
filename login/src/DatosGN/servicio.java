package DatosGN;

/**
 *
 * @author HP
 */
public class servicio {
    int id_servicio;
    int id_producto;
    String descripcion;
    float precio;
    String imgen;

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImgen() {
        return imgen;
    }

    public void setImgen(String imgen) {
        this.imgen = imgen;
    }
    
}

