package med.voll.api.Paciente;

import med.voll.api.Model.Direccion;
import med.voll.api.Model.Paciente;

public record DatosDetallesPaciente(Long id, String nombre, String email,
                                    Integer DNI,
                                    String telefono,
                                    Direccion direccion) {

    public DatosDetallesPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDNI(), paciente.getTelefono(), paciente.getDireccion());
    }
}
