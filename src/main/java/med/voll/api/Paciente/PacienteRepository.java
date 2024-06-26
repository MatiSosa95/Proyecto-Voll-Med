package med.voll.api.Paciente;

import med.voll.api.Model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT p.activo FROM Paciente p WHERE  p.id=:idPaciente""")
    Boolean findActivoById(Long idPaciente);

    @Query("""
            select p.activo from Paciente p where p.id=.idPaciente""")
    Boolean findAllByActivoId(Long idPaciente);
}
