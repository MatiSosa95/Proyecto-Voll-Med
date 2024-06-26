package med.voll.api.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Paciente.DatosActualizacionPaciente;
import med.voll.api.Paciente.DatosRegistroPaciente;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Integer DNI;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Paciente(DatosRegistroPaciente datos){
    this.activo=true;
    this.nombre=datos.nombre();
    this.email= datos.email();
    this.telefono= datos.telefono();
    this.DNI= datos.DNI();
    this.direccion= new Direccion(datos.datosDireccion());
    }

    public void actualizarInformaciones(DatosActualizacionPaciente datos){
        if(datos.nombre()!=null){
            this.nombre= datos.nombre();
        }
        if (datos.telefono()!=null){
            this.telefono= datos.telefono();
        }

        if(datos.datosDireccion()!=null){
            this.direccion.actualizarDatos(datos.datosDireccion());
        }
    }

    public void eliminar(){
        this.activo=false;
    }

}
