package pch.huellaschile.msconsultasmedicas.domain.gateways;

import pch.huellaschile.msconsultasmedicas.domain.entities.Consulta;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;

import java.util.List;
import java.util.Optional;

public interface ConsultaGateway {
    List<Consulta> getAll();
    Optional<Consulta> findById(int id);
    Consulta saveConsulta(Consulta consulta);

    boolean existsByEstadoConsultaContainingIgnoreCaseAndIdMascota(String estado, int id);

    boolean existsByIdMascotaAndIdVeterinaria(int idMasc, int idVet);

    Consulta updateConsulta(Consulta consulta);

    void deleteConsulta(Consulta consulta);
}
