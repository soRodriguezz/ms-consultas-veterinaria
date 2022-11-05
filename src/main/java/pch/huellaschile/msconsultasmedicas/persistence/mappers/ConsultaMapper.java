package pch.huellaschile.msconsultasmedicas.persistence.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pch.huellaschile.msconsultasmedicas.domain.entities.Consulta;
import pch.huellaschile.msconsultasmedicas.persistence.models.ConsultaDAO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {
    @Mappings({
            @Mapping(source = "idConsulta", target = "idConsulta"),
            @Mapping(source = "idMascota", target = "idMascota"),
            @Mapping(source = "mascota", target = "mascota"),
            @Mapping(source = "idDueno", target = "idDueno"),
            @Mapping(source = "dueno", target = "dueno"),
            @Mapping(source = "idVeterinaria", target = "idVeterinaria"),
            @Mapping(source = "veterinaria", target = "veterinaria"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "formaPago", target = "formaPago"),
            @Mapping(source = "descripcionProceso", target = "descripcionProceso"),
            @Mapping(source = "estadoConsulta", target = "estadoConsulta"),
            @Mapping(source = "estaEnTratamiento", target = "estaEnTratamiento"),
    })
    Consulta toConsulta(ConsultaDAO dao);

    List<Consulta> toConsultas(List<ConsultaDAO> daos);

    @InheritInverseConfiguration
    ConsultaDAO toConsultaDAO(Consulta consulta);
}
