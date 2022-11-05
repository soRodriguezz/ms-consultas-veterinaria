package pch.huellaschile.msconsultasmedicas.persistence;

import org.springframework.stereotype.Repository;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.gateways.DuenoGateway;
import pch.huellaschile.msconsultasmedicas.persistence.crud.DuenoCrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.mappers.DuenoMapper;
import pch.huellaschile.msconsultasmedicas.persistence.models.DuenoDAO;

import java.util.List;
import java.util.Optional;

@Repository
public class DuenoRepository implements DuenoGateway {

    private final DuenoCrudRepository crudRepo;

    private final DuenoMapper mapper;

    public DuenoRepository(DuenoCrudRepository crudRepo, DuenoMapper mapper) {
        this.crudRepo = crudRepo;
        this.mapper = mapper;
    }

    @Override
    public List<Dueno> getAll() {
        List<DuenoDAO> daos = (List<DuenoDAO>) crudRepo.findAll();
        return mapper.toDuenos(daos);
    }

    @Override
    public Dueno saveDueno(Dueno dueno) {
        DuenoDAO dao = crudRepo.save(mapper.toDuenoDAO(dueno));
        return mapper.toDueno(dao);
    }

    @Override
    public Dueno updateDueno(Dueno dueno) {
        DuenoDAO dao = crudRepo.save(mapper.toDuenoDAO(dueno));
        return mapper.toDueno(dao);
    }

    @Override
    public Optional<Dueno> findById(int id) {
        DuenoDAO dao = crudRepo.findById(id).get();
        return Optional.ofNullable(mapper.toDueno(dao));
    }

    @Override
    public void deleteDueno(Dueno dueno) {
        crudRepo.delete(mapper.toDuenoDAO(dueno));
    }
}
