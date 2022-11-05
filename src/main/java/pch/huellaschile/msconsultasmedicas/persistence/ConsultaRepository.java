package pch.huellaschile.msconsultasmedicas.persistence;

import org.springframework.stereotype.Repository;
import pch.huellaschile.msconsultasmedicas.domain.entities.Consulta;
import pch.huellaschile.msconsultasmedicas.domain.gateways.ConsultaGateway;
import pch.huellaschile.msconsultasmedicas.persistence.crud.ConsultaCrudRepository;
import pch.huellaschile.msconsultasmedicas.persistence.mappers.ConsultaMapper;
import pch.huellaschile.msconsultasmedicas.persistence.models.ConsultaDAO;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultaRepository implements ConsultaGateway {
    private final ConsultaCrudRepository crudRepo;

    private final ConsultaMapper mapper;

    public ConsultaRepository(ConsultaCrudRepository crudRepo, ConsultaMapper mapper) {
        this.crudRepo = crudRepo;
        this.mapper = mapper;
    }

    @Override
    public List<Consulta> getAll() {
        List<ConsultaDAO> daos = (List<ConsultaDAO>) crudRepo.findAll();
        return mapper.toConsultas(daos);
    }

    @Override
    public Optional<Consulta> findById(int id) {
        ConsultaDAO dao = crudRepo.findById(id).get();
        return Optional.ofNullable(mapper.toConsulta(dao));
    }

    @Override
    public Consulta saveConsulta(Consulta consulta) {
        ConsultaDAO dao = crudRepo.save(mapper.toConsultaDAO(consulta));
        return mapper.toConsulta(dao);
    }

    @Override
    public boolean existsByEstadoConsultaContainingIgnoreCaseAndIdMascota(String estado, int id) {
        return crudRepo.existsByEstadoConsultaContainingIgnoreCaseAndIdMascota(estado, id);
    }

    @Override
    public boolean existsByIdMascotaAndIdVeterinaria(int idMasc, int idVet) {
        return crudRepo.existsByIdMascotaAndIdVeterinaria(idMasc, idVet);
    }

    @Override
    public Consulta updateConsulta(Consulta consulta) {
        ConsultaDAO dao = crudRepo.save(mapper.toConsultaDAO(consulta));
        return mapper.toConsulta(dao);
    }

    @Override
    public void deleteConsulta(Consulta consulta) {
        crudRepo.delete(mapper.toConsultaDAO(consulta));
    }
}
