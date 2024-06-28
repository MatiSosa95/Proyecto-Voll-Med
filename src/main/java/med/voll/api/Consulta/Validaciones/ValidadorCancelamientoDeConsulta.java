package med.voll.api.Consulta.Validaciones;

import med.voll.api.Consulta.DatosAgendaConsulta;
import med.voll.api.Consulta.DatosCancelamientoConsulta;

public interface ValidadorCancelamientoDeConsulta {
    public void validar(DatosCancelamientoConsulta datos);
}
