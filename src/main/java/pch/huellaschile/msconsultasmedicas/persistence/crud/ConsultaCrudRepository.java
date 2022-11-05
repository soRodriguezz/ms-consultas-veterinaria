package pch.huellaschile.msconsultasmedicas.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.models.ConsultaDAO;

public interface ConsultaCrudRepository extends CrudRepository<ConsultaDAO, Integer> {
    Boolean existsByEstadoConsultaContainingIgnoreCaseAndIdMascota(String estado, int id);
    Boolean existsByIdMascotaAndIdVeterinaria(int idMasc, int idVet);
}
