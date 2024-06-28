package med.voll.api.Consulta;

import med.voll.api.Consulta.Validaciones.ValidadorDeConsultas;
import med.voll.api.Infra.Errores.ValidacionDeIntegridad;
import med.voll.api.Medico.IMedicoRepository;
import med.voll.api.Model.Medico;
import med.voll.api.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private IMedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores; //Esto permite traer todos los validadores que queramos. Y si eliminamos uno
    //no afecta en nada nuestro proyecto.

    public DatosDetalleConsulta agendar(DatosAgendaConsulta datos){
        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este Id para el paciente no fue encontrado");
        }

        if (datos.idMedico()!=null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este Id para el medico no fue encontrado");
        }

        /*Aca lo que hacemos en enviarle los datos a cada de los validadores y verificar si se cumplen con las condiciones
        de cada validador.
         */
        validadores.forEach(v->v.validar(datos));

        var paciente= pacienteRepository.findById(datos.idPaciente()).get();

        var medico= seleccionarMedico(datos);

        if(medico==null){
            throw new ValidacionDeIntegridad("No existen medicos disponible para este horario y especialidad");
        }

        var consulta= new Consulta(medico,paciente,datos.fecha());

        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
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

    public void cancelar(DatosCancelamientoConsulta datos){
        if(consultaRepository.existsById(datos.idConsulta())){
            throw new ValidacionDeIntegridad("Id de la consulta informada no existe");
        }

        validadoresCancelamiento.forEach(v->v.validar(datos));

        var consulta= consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivos());
    }
}
