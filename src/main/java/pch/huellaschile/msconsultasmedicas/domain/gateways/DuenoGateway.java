package pch.huellaschile.msconsultasmedicas.domain.gateways;

import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;

import java.util.List;
import java.util.Optional;

public interface DuenoGateway {
    List<Dueno> getAll();

    Dueno saveDueno(Dueno dueno);

    Dueno updateDueno(Dueno dueno);

    Optional<Dueno> findById(int id);

    void deleteDueno(Dueno dueno);
}
