package med.voll.api.Model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.Medico.DatosActualizarMedico;
import med.voll.api.Medico.DatosRegistroMedico;
import med.voll.api.Medico.Especialidad;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Integer DNI;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo=true;
        this.nombre= datosRegistroMedico.nombre();
        this.email= datosRegistroMedico.email();
        this.telefono= datosRegistroMedico.telefono();
        this.DNI= datosRegistroMedico.DNI();
        this.especialidad= datosRegistroMedico.especialidad();
        this.direccion= new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre()!=null){
            this.nombre= datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.DNI()!=null){
            this.DNI= datosActualizarMedico.DNI();
        }

        if (datosActualizarMedico.datosDireccion()!=null){
            this.direccion= direccion.actualizarDatos(datosActualizarMedico.datosDireccion());
        }

    }

    public void desativarMedico() {
        this.activo=false;
    }
}
