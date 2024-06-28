package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.ConsultaRepository;
import med.voll.api.Consulta.DatosAgendaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendaConsulta datos){
        var primerHorario =datos.fecha().withHour(7);
        var ultimoHorario= datos.fecha().withHour(18);

        var pacienteConConsulta= repository.existsByPacienteIdAndDataBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese dia");
        }
    }
}
