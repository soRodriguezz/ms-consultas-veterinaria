package pch.huellaschile.msconsultasmedicas.persistence;

import org.springframework.stereotype.Repository;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.domain.gateways.MascotaGateway;
import pch.huellaschile.msconsultasmedicas.persistence.crud.MascotaCrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.mappers.MascotaMapper;
import pch.huellaschile.msconsultasmedicas.persistence.models.MascotaDAO;

import java.util.List;
import java.util.Optional;

@Repository
public class MascotaRepository implements MascotaGateway {

    private final MascotaCrudRepository crudRepo;

    private final MascotaMapper mapper;

    public MascotaRepository(MascotaCrudRepository crudRepo, MascotaMapper mapper) {
        this.crudRepo = crudRepo;
        this.mapper = mapper;
    }

    @Override
    public List<Mascota> getAll() {
        List<MascotaDAO> daos = (List<MascotaDAO>) crudRepo.findAll();
        return mapper.toMascotas(daos);
    }

    @Override
    public Mascota saveMascota(Mascota mascota) {
        MascotaDAO dao = crudRepo.save(mapper.toMascotaDAO(mascota));
        return mapper.toMascota(dao);
    }

    @Override
    public Mascota updateMascota(Mascota mascota) {
        MascotaDAO dao = crudRepo.save(mapper.toMascotaDAO(mascota));
        return mapper.toMascota(dao);
    }

    @Override
    public Optional<Mascota> findById(int id) {
        MascotaDAO dao = crudRepo.findById(id).get();
        return Optional.ofNullable(mapper.toMascota(dao));
    }

    @Override
    public void deleteMascota(Mascota mascota) {
        crudRepo.delete(mapper.toMascotaDAO(mascota));
    }
}
