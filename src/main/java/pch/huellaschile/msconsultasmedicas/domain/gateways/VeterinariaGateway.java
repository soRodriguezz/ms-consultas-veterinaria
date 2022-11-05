package pch.huellaschile.msconsultasmedicas.domain.gateways;

import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;

import java.util.List;
import java.util.Optional;

public interface VeterinariaGateway {
    List<Veterinaria> getAll();

    Veterinaria saveVeterinaria(Veterinaria veterinaria);

    Veterinaria updateVeterinaria(Veterinaria veterinaria);

    Optional<Veterinaria> findById(int id);

    void deleteVeterinaria(Veterinaria veterinaria);
}
