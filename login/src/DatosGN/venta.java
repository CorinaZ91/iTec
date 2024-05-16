
package DatosGN;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import sun.util.resources.LocaleData;

public class venta {
    int id_venta;
    int id_usuario;
    LocalDate fecha;
    Timestamp hora;
    float total;

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    public void setHoraFromLocalTime(LocalTime localTime) {
        // Convertir LocalTime a Timestamp
        this.hora = Timestamp.valueOf(LocalDate.now().atTime(localTime));
    }

    
}
