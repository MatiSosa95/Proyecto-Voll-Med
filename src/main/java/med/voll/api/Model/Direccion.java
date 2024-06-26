package med.voll.api.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.Direccion.DatosDireccion;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private Integer numero;
    private String manzana;
    private String departamento;
    private String provincia;

    public Direccion(DatosDireccion direccion) {
        this.calle= direccion.calle();
        this.numero= direccion.numero();
        this.manzana= direccion.manzana();
        this.departamento= direccion.departamento();
        this.provincia= direccion.provincia();
    }

    public Direccion actualizarDatos(DatosDireccion datosDireccion) {
        this.calle=datosDireccion.calle();
        this.numero= datosDireccion.numero();
        this.manzana= datosDireccion.manzana();
        this.departamento= datosDireccion.departamento();
        this.provincia= datosDireccion.provincia();
        return this;
    }
}
