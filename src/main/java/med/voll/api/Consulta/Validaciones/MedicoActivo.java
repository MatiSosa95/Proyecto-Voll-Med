package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.DatosAgendaConsulta;
import med.voll.api.Medico.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicoActivo {
    @Autowired
    private IMedicoRepository repository;

    public void validad(DatosAgendaConsulta datos){
        if(datos.idMedico()==null){
            return;
        }

        var medicoActivo= repository.findAllByActivoId(datos.idMedico());
        if (!medicoActivo){
            throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
        }
    }
}
