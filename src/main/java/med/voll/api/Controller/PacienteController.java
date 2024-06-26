package med.voll.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Model.Paciente;
import med.voll.api.Paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriComponentsBuilder){
        var paciente= new Paciente(datos);
        pacienteRepository.save(paciente);

        var uri= uriComponentsBuilder.path("/paciente/{id]").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault( sort = {"nombre"})Pageable paginacion){
        var page= pacienteRepository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionPaciente datosActualizacionPaciente){
        var paciente= pacienteRepository.getReferenceById(datosActualizacionPaciente.id());
        paciente.actualizarInformaciones(datosActualizacionPaciente);

        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        var paciente= pacienteRepository.getReferenceById(id);
        paciente.eliminar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id)
    {
        var paciente= pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }
}
