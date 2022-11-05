package pch.huellaschile.msconsultasmedicas.persistence.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.persistence.models.DuenoDAO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DuenoMapper {
    @Mappings({
            @Mapping(source = "idDueno", target = "idDueno"),
            @Mapping(source = "nombre", target = "nombre")
    })
    Dueno toDueno(DuenoDAO dao);

    List<Dueno> toDuenos(List<DuenoDAO> daos);

    @InheritInverseConfiguration
    DuenoDAO toDuenoDAO(Dueno dueno);
}
