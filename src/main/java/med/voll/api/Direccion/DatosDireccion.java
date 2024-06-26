package med.voll.api.Direccion;

import jakarta.validation.constraints.NotBlank;// anotacion de validation de spring
import jakarta.validation.constraints.NotNull;

public record DatosDireccion(@NotBlank  String calle,
                             @NotNull Integer numero,
                             @NotBlank String manzana,
                             @NotBlank String departamento,
                             @NotBlank String provincia) {
}
