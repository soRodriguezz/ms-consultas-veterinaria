package pch.huellaschile.msconsultasmedicas.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.models.DuenoDAO;

public interface DuenoCrudRepository extends CrudRepository<DuenoDAO, Integer> {
}
