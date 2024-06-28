package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.ConsultaRepository;
import med.voll.api.Consulta.DatosAgendaConsulta;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    private ConsultaRepository repository;

    public  void validar(DatosAgendaConsulta datos){
        if (datos.idMedico()==null){
            return;
        }
        var medicoConConsulta= repository.existsByMedicoIdAndData(datos.idMedico(), datos.fecha());

        if (medicoConConsulta){
            throw new ValidationException("Este medico ya tiene una consulta en ese horario");
        }

    }
}
