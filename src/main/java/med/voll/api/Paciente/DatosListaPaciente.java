package med.voll.api.Paciente;

import med.voll.api.Model.Paciente;

public record DatosListaPaciente(Long id, String nombre, String email, Integer DNI) {

    public DatosListaPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDNI());
    }
}
