package med.voll.api.Consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(@NotNull Long idConsulta, @NotNull MotivoCancelamiento motivo) {
}
