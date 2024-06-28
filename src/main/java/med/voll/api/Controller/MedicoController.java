package med.voll.api.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Direccion.DatosDireccion;
import med.voll.api.Medico.*;
import med.voll.api.Model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private IMedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder){
        Medico medico= medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico respuestaMedico= new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getDNI(), new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getNumero(),
                medico.getDireccion().getManzana(),medico.getDireccion().getDepartamento(),medico.getDireccion().getProvincia()));
        URI url= uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> ListadoMedico(@PageableDefault(size = 2) Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        //funcion paginacion:
        //Pageable tiene valores por defecto, pero con PageableDefault se puede reescribir dichos valores.
        //size para el tamaño de las paginas y order para ordenar
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional//Una vez hecho el cambio la transaccion se va a liberar. Una vez temrine se hace un commit a mivel db
    //Transactional abre una transaccion en la db y una vez los datos son cambiados, hace el commit
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico= medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
        medico.getTelefono(), medico.getDNI(), new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getNumero(),
                medico.getDireccion().getManzana(),medico.getDireccion().getDepartamento(),medico.getDireccion().getProvincia())));

    }
    //Delete de DB
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void borrarMedico(@PathVariable Long id){
//        Medico medico= medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }

    //Delete Logico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borradoLogicoMedico(@PathVariable Long id){
        Medico medico= medicoRepository.getReferenceById(id);
        medico.desativarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedicos(@PathVariable Long id){
        Medico medico= medicoRepository.getReferenceById(id);
        var DatosMedicos= new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getDNI(), new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getNumero(),
                medico.getDireccion().getManzana(),medico.getDireccion().getDepartamento(),medico.getDireccion().getProvincia()));
        return ResponseEntity.ok(DatosMedicos);
    }
}
