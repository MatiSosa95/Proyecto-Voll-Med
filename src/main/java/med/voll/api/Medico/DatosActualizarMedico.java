package med.voll.api.Medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id,
                                    String nombre,
                                    Integer DNI,
                                    DatosDireccion datosDireccion) {
}
