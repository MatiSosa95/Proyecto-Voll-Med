package med.voll.api.Medico;

import med.voll.api.Direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id,
                                   String nombre,
                                   String email,
                                   String telefono,
                                   Integer DNI,
                                   DatosDireccion direccion) {
}
