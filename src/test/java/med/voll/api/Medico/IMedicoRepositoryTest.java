package med.voll.api.Medico;

import med.voll.api.Consulta.Consulta;
import med.voll.api.Direccion.DatosDireccion;
import med.voll.api.Model.Medico;
import med.voll.api.Model.Paciente;
import med.voll.api.Paciente.DatosRegistroPaciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;
//Aca indicamos que vamos a trabajar con persistencia en DB
@DataJpaTest
//Aqui se indica que no vamos a reemplazar la db que estamos usando y que usaremos una db externa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Aquie le decimos que perfil vamos a usar. Usaremos el perfil TEST
@ActiveProfiles("test")
class IMedicoRepositoryTest {

    @Autowired
    private IMedicoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {

        //Dado un conjunto de valores
        var proximoLunes10H= LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico= registrarMedico("José", "jose@mail.com", 38745921, Especialidad.CARDIOLOGIA);
        var paciente=registrarPaciente("Antonio", "antonio@gmail.com",7894561);
        registrarConsulta(medico, paciente, proximoLunes10H);
        //Entonces
        var medicoLibre= repository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes10H);
        //COmparamos que ese valor de la db sea nullo
        assertNull(medicoLibre);
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, Integer DNI, Especialidad especialidad){
        var medico= new Medico(datosMedico(nombre, email, DNI, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, Integer DNI){
        var paciente= new Paciente(datosPaciente(nombre, email, DNI));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email,  Integer DNI) {
        return new DatosRegistroPaciente(nombre, email,null, DNI, datosDireccion());
    }

    private  DatosRegistroMedico datosMedico(String nombre, String email, Integer DNI, Especialidad especialidad){
        return new DatosRegistroMedico(nombre, email, null, DNI, especialidad,datosDireccion());
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion("chacabuco", 2245, "F", "Maipu", "Buenos Aires");
    }


}