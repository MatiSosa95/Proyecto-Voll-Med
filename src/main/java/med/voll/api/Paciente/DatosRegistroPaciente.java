package med.voll.api.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.voll.api.Direccion.DatosDireccion;

public record DatosRegistroPaciente(@NotBlank String nombre, @Email @NotBlank String email,
                                    @NotBlank @Size(min = 0, max = 15) String telefono,
                                    @NotNull Integer DNI,
                                    @NotNull @Valid DatosDireccion datosDireccion){
}
