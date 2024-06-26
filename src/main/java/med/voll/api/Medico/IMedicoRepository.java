package med.voll.api.Medico;

import med.voll.api.Model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    //primer cambio en IJ
    @Query("""
            SELECT m from Medico m 
            where m.activo=1 and 
            m.especialidad=:especialidad and 
            m.id not in( 
            select c.medico_id from Consulta c
            where
            c.data=:fecha)
            order by rand() limit 1""")
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
            select m.activo from Medico m where m.id=:idMedico""")
    Boolean findAllByActivoId(Long idMedico);
}
