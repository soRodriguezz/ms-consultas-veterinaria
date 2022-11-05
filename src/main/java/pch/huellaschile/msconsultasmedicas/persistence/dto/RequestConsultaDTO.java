package pch.huellaschile.msconsultasmedicas.persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestConsultaDTO {
    private int idMascota;
    private int idDueno;
    private int idVeterinaria;
    private int valor;
    private String formaPago;
    private String descripcionProceso;
    private String estadoConsulta;
    private boolean estaEnTratamiento;
}
