package pch.huellaschile.msconsultasmedicas.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.models.VeterinariaDAO;

public interface VeterinariaCrudRepository extends CrudRepository<VeterinariaDAO, Integer> {
}
