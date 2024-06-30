package med.voll.api.Consulta.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Consulta.DatosAgendaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@SuppressWarnings("all")
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgendaConsulta datos){
        var ahora= LocalDateTime.now();
        var horaDeConsulta= datos.fecha();
        var diferenciaDe30Min= Duration.between(ahora, horaDeConsulta).toMinutes()<30;

        if (diferenciaDe30Min){
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipacion");
        }
    }
}
