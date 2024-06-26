package med.voll.api.Medico;

import med.voll.api.Model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface IMedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT m from Medico m 
            where m.activo=1 and 
            m.especialidad=:especialidad and 
            m.id not in( 
            select c.medico.id from Consulta c
            c.data=:fecha)
            order by rand() limit 1""")
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
}
