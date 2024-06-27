package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.ConsultaRepository;
import med.voll.api.Consulta.DatosAgendaConsulta;
import org.springframework.beans.factory.annotation.Autowired;

public class PacienteSinConsulta {

    @Autowired
    private ConsultaRepository repository;
    public void validad (DatosAgendaConsulta datos){
        var primerHorario =datos.fecha().withHour(7);
        var ultimoHorario= datos.fecha().withHour(18);

        var pacienteConConsulta= repository.existByPacienteIdAndDataBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese dia");
        }
    }
}
