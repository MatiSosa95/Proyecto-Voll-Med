package med.voll.api.Consulta;

import med.voll.api.Infra.Errores.ValidacionDeIntegridad;
import med.voll.api.Medico.IMedicoRepository;
import med.voll.api.Model.Medico;
import med.voll.api.Model.Paciente;
import med.voll.api.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private IMedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DatosAgendaConsulta datos){
        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este Id para el paciente no fue encontrado");
        }

        if (datos.idMedico()!=null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este Id para el medico no fue encontrado");
        }

        var paciente= pacienteRepository.findById(datos.idPaciente()).get();

        var medico= seleccionarMedico(datos);


        var consulta= new Consulta(null, medico,paciente,datos.fecha());

        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendaConsulta datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad()==null){
            throw new ValidacionDeIntegridad("debe seleciconarse una especialidad");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }
}
