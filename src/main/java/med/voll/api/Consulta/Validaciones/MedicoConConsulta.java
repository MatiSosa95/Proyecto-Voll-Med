package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.ConsultaRepository;
import med.voll.api.Consulta.DatosAgendaConsulta;

public class MedicoConConsulta {

    private ConsultaRepository repository;

    public  void validad(DatosAgendaConsulta datos){
        if (datos.idMedico()==null){
            return;
        }
        var medicoConConsulta= repository.existsByMedicoIdAndData(datos.idMedico(), datos.fecha());

        if (medicoConConsulta){
            throw new ValidationException("Este medico ya tiene una consulta en ese horario");
        }

    }
}
