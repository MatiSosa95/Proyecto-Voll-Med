package med.voll.api.Controller;

import med.voll.api.Consulta.AgendaDeConsultaService;
import med.voll.api.Consulta.DatosAgendaConsulta;
import med.voll.api.Consulta.DatosDetalleConsulta;
import med.voll.api.Medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    //Tom aun objeto del tipo java y los transforma en un tipo json
    private JacksonTester<DatosAgendaConsulta> AgendarConsultaJacksonTester;

    @Autowired
    //Transforma un tipo json en un tipo Java
    private JacksonTester<DatosDetalleConsulta> detalleConsultaJacksonTester;

    @MockBean
    /*Al enviar el json al controllador y leugo a la clase servicio, para luego ir a la clase reposirotio apra hacer la busqueda en la db
    retorna los valores de los datos de la consulta. Para ello debo simular esa clase de servicio para indicarle a la app que no debe hacer una busqueda
    dentro de la db real, sino que le vamos a indicar cual va a ser el retorno.

    * */
    private AgendaDeConsultaService agendaDeConsultaService;

    @Test
    @DisplayName("Deberia retornar estado 400 cuando los datos ingresados sean invalidados")
    @WithMockUser
    void agendarEscenario1() throws Exception {
        var response= mvc.perform(post("/consultas")).andReturn().getResponse();
        //Then
        //Assertthat deprecado
        assertThat(response.getStatus()).isEqualsTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado 200 cuando los datos ingresados sean invalidados")
    @WithMockUser
    void agendarEscenario2() throws Exception {
        var fecha= LocalDateTime.now().plusHours(1);

        var especialidad= Especialidad.CARDIOLOGIA;

        var datos= new DatosDetalleConsulta(null, 2l, 5l, fecha);

        //Cuando se realice el agendamiento de esta consulta
        when(agendaDeConsultaService.agendar(any())).thenReturn(datos);

        var response= mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AgendarConsultaJacksonTester.write(new DatosAgendaConsulta(null,  2l, 5l, fecha,especialidad)).getJson())
        ).andReturn().getResponse();
        //Then
        //Assertthat deprecado
        assertThat(response.getStatus()).isEqualsTo(HttpStatus.OK.value());

        //COmparamos lo que nos trae con lo que deberia traer.
        var jsonEsperado= detalleConsultaJacksonTester.write(datos).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}