package pch.huellaschile.msconsultasmedicas.persistence;

import org.springframework.stereotype.Repository;
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.domain.gateways.VeterinariaGateway;
import pch.huellaschile.msconsultasmedicas.persistence.crud.VeterinariaCrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.mappers.VeterinariaMapper;
import pch.huellaschile.msconsultasmedicas.persistence.models.VeterinariaDAO;

import java.util.List;
import java.util.Optional;

@Repository
public class VeterinariaRepository implements VeterinariaGateway {

    private final VeterinariaCrudRepository crudRepo;

    private final VeterinariaMapper mapper;

    public VeterinariaRepository(VeterinariaCrudRepository crudRepo, VeterinariaMapper mapper) {
        this.crudRepo = crudRepo;
        this.mapper = mapper;
    }

    @Override
    public List<Veterinaria> getAll() {
        List<VeterinariaDAO> daos = (List<VeterinariaDAO>) crudRepo.findAll();
        return mapper.toVeterinarias(daos);
    }

    @Override
    public Veterinaria saveVeterinaria(Veterinaria veterinaria) {
        VeterinariaDAO dao = crudRepo.save(mapper.toVeterinariaDAO(veterinaria));
        return mapper.toVeterinaria(dao);
    }

    @Override
    public Veterinaria updateVeterinaria(Veterinaria veterinaria) {
        VeterinariaDAO dao = crudRepo.save(mapper.toVeterinariaDAO(veterinaria));
        return mapper.toVeterinaria(dao);
    }

    @Override
    public Optional<Veterinaria> findById(int id) {
        VeterinariaDAO dao = crudRepo.findById(id).get();
        return Optional.ofNullable(mapper.toVeterinaria(dao));
    }

    @Override
    public void deleteVeterinaria(Veterinaria veterinaria) {
        crudRepo.delete(mapper.toVeterinariaDAO(veterinaria));
    }
}
