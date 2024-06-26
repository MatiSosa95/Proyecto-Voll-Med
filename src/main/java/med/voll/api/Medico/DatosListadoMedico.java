package med.voll.api.Medico;

import med.voll.api.Model.Medico;

public record DatosListadoMedico(Long id, String nombre,
                                 String especialidad,
                                 Integer DNI,
                                 String email) {
    public DatosListadoMedico(Medico medico) {
        this(medico.getId(),medico.getNombre(),medico.getEspecialidad().toString(), medico.getDNI(),medico.getEmail());
    }
}
