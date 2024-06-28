package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.DatosAgendaConsulta;
import med.voll.api.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {
    public void validar(DatosAgendaConsulta datos){
        var domingo= DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura= datos.fecha().getHour()<7;
        var despuesDeCierre=datos.fecha().getHour()>19;
        if (domingo || antesDeApertura || despuesDeCierre){
            throw new ValidationException("El horario de atencion de la clinica es de Lunes a Sabado de 07:00 a 19:00 horas");
        }
    }
}
