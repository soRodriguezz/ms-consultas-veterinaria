package pch.huellaschile.msconsultasmedicas.domain.gateways;

import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaGateway {
    List<Mascota> getAll();

    Mascota saveMascota(Mascota mascota);

    Mascota updateMascota(Mascota mascota);

    Optional<Mascota> findById(int id);

    void deleteMascota(Mascota mascota);
}
