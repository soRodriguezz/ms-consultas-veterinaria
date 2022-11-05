package pch.huellaschile.msconsultasmedicas.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.models.MascotaDAO;

public interface MascotaCrudRepository extends CrudRepository<MascotaDAO, Integer> {
}
