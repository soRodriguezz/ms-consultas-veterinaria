package pch.huellaschile.msconsultasmedicas.persistence.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.persistence.models.VeterinariaDAO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeterinariaMapper {
    @Mappings({
            @Mapping(source = "idVeterinaria", target = "idVeterinaria"),
            @Mapping(source = "nombre", target = "nombre")
    })
    Veterinaria toVeterinaria(VeterinariaDAO dao);

    List<Veterinaria> toVeterinarias(List<VeterinariaDAO> daos);

    @InheritInverseConfiguration
    VeterinariaDAO toVeterinariaDAO(Veterinaria veterinaria);
}
