package med.voll.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Consulta.AgendaDeConsultaService;
import med.voll.api.Consulta.DatosAgendaConsulta;
import med.voll.api.Consulta.DatosDetalleConsulta;
import med.voll.api.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@Operation(security = { @SecurityRequirement(name = "bearer-key") })
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendaConsulta datos)throws ValidacionDeIntegridad {

        var response= agendaDeConsultaService.agendar(datos);

        return  ResponseEntity.ok(response);
    }
}
