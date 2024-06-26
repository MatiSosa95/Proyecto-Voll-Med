package med.voll.api.Consulta.Validaciones;

import med.voll.api.Consulta.DatosAgendaConsulta;
import med.voll.api.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PacienteActivo {

    @Autowired
    private PacienteRepository repository;

    public void validad(DatosAgendaConsulta datos){
        if(datos.idPaciente()==null){
            return;
        }

        var pacienteActivo= repository.findAllByActivoTrue();
    }
}
