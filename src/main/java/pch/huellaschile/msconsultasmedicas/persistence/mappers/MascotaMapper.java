package pch.huellaschile.msconsultasmedicas.persistence.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.persistence.models.MascotaDAO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mappings({
            @Mapping(source = "idMascota", target = "idMascota"),
            @Mapping(source = "nombre", target = "nombre")
    })
    Mascota toMascota(MascotaDAO dao);

    List<Mascota> toMascotas(List<MascotaDAO> daos);

    @InheritInverseConfiguration
    MascotaDAO toMascotaDAO(Mascota mascota);
}
