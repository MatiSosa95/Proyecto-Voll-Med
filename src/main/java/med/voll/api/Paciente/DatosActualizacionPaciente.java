package med.voll.api.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Direccion.DatosDireccion;

public record DatosActualizacionPaciente(@NotNull Long id,
                                        String nombre,
                                        String telefono,
                                        DatosDireccion datosDireccion) {
}
