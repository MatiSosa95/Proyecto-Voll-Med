package med.voll.api.Consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendaConsulta(Long id,
                                  @NotNull Long idPaciente,
                                  Long idMedico,
                                  @NotNull @Future LocalDateTime fecha,
                                  Especialidad especialidad) {
}
