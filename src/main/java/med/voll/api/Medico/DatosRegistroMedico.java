package med.voll.api.Medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;//Que tenga formato de email
import jakarta.validation.constraints.NotBlank;//que un string no lleguen en blanco
import jakarta.validation.constraints.NotNull;//Que lo que no sea string no lleguen nulas
import jakarta.validation.constraints.Pattern;//patron que indica que tiene que tener entre 4 y 6 digitos
import med.voll.api.Direccion.DatosDireccion;

public record DatosRegistroMedico(@NotBlank(message = "Nombre Obligatorio") String nombre,
                                  @NotBlank(message = "Email Obligatorio") @Email String email,
                                  @NotBlank(message = "Telefono obligatorio")  String telefono,
                                  @NotNull(message = "DNI obligatorio")  Integer DNI,
                                  @NotNull Especialidad especialidad,
                                  @NotNull @Valid DatosDireccion direccion)
//valid es una anotacion que nos permite validad que la direccion que estamos recibiendo contenga todos
    // los datos
{
}
