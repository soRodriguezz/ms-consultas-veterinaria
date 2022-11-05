package pch.huellaschile.msconsultasmedicas.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pch.huellaschile.msconsultasmedicas.domain.entities.Consulta;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.domain.exception.ConsultOpenOrTreatmentException;
import pch.huellaschile.msconsultasmedicas.domain.exception.OpenConsultException;
import pch.huellaschile.msconsultasmedicas.domain.exception.OtherVeterinarianException;
import pch.huellaschile.msconsultasmedicas.domain.gateways.ConsultaGateway;
import pch.huellaschile.msconsultasmedicas.domain.gateways.DuenoGateway;
import pch.huellaschile.msconsultasmedicas.domain.gateways.MascotaGateway;
import pch.huellaschile.msconsultasmedicas.domain.gateways.VeterinariaGateway;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestConsultaDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaGateway gateway;

    @Autowired
    private MascotaGateway gatewayMasc;

    @Autowired
    private VeterinariaGateway gatewayVet;

    @Autowired
    private DuenoGateway gatewayDueno;

    public List<Consulta> getAll() {
        return gateway.getAll();
    }

    public Optional<Consulta> findById(int id) {
        return gateway.findById(id);
    }

    public Consulta save(RequestConsultaDTO dto) throws OpenConsultException, OtherVeterinarianException {

        boolean consulta1 = gateway.existsByEstadoConsultaContainingIgnoreCaseAndIdMascota("abierta", dto.getIdMascota());
        Object[] args = {dto};

        if(consulta1) {
            throw new OpenConsultException("consulta.consultopen.message", args);
        }

        boolean existMascota = gateway.existsByIdMascotaAndIdVeterinaria(dto.getIdMascota(), dto.getIdVeterinaria());

        if(existMascota) {
            throw new OtherVeterinarianException("consulta.otherveterinarian.message", args);
        }

        Optional<Mascota> mascota = gatewayMasc.findById(dto.getIdMascota());

        Optional<Veterinaria> veterinaria = gatewayVet.findById(dto.getIdVeterinaria());

        Optional<Dueno> dueno = gatewayDueno.findById(dto.getIdDueno());

        Consulta consulta = new Consulta();

        consulta.setIdMascota(dto.getIdMascota());
        consulta.setMascota(mascota.get());
        consulta.setIdVeterinaria(dto.getIdVeterinaria());
        consulta.setVeterinaria(veterinaria.get());
        consulta.setIdDueno(dto.getIdDueno());
        consulta.setDueno(dueno.get());
        consulta.setValor(dto.getValor());
        consulta.setFormaPago(dto.getFormaPago());
        consulta.setDescripcionProceso(dto.getDescripcionProceso());
        consulta.setEstadoConsulta("abierta");
        consulta.setEstaEnTratamiento(true);

        return gateway.saveConsulta(consulta);
    }

    public Consulta update(int id, RequestConsultaDTO dto) {
        Optional<Consulta> consulta1 = gateway.findById(id);

        Optional<Mascota> mascota = gatewayMasc.findById(dto.getIdMascota());

        Optional<Dueno> dueno = gatewayDueno.findById(dto.getIdDueno());

        Consulta consulta = new Consulta();

        consulta.setIdConsulta(id);
        consulta.setIdMascota(dto.getIdMascota());
        consulta.setMascota(mascota.get());
        consulta.setIdVeterinaria(consulta1.get().getIdVeterinaria());
        consulta.setVeterinaria(consulta1.get().getVeterinaria());
        consulta.setIdDueno(dto.getIdDueno());
        consulta.setDueno(dueno.get());
        consulta.setValor(dto.getValor());
        consulta.setFormaPago(dto.getFormaPago());
        consulta.setDescripcionProceso(dto.getDescripcionProceso());
        consulta.setEstadoConsulta("abierta");
        consulta.setEstaEnTratamiento(true);

        return gateway.updateConsulta(consulta);
    }

    public Consulta estadoCerrada(int id) {
        Optional<Consulta> consulta1 = gateway.findById(id);

        Optional<Mascota> mascota = gatewayMasc.findById(consulta1.get().getIdMascota());

        Optional<Dueno> dueno = gatewayDueno.findById(consulta1.get().getIdDueno());

        Consulta consulta = new Consulta();

        if(consulta1.get().isEstaEnTratamiento()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tratamiento esta activo");
        }

        if(consulta1.get().getValor() != 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor del tratamiento no esta cancelado");
        }

        consulta.setIdConsulta(id);
        consulta.setIdMascota(consulta1.get().getIdMascota());
        consulta.setMascota(mascota.get());
        consulta.setIdVeterinaria(consulta1.get().getIdVeterinaria());
        consulta.setVeterinaria(consulta1.get().getVeterinaria());
        consulta.setIdDueno(consulta1.get().getIdDueno());
        consulta.setDueno(dueno.get());
        consulta.setValor(consulta1.get().getValor());
        consulta.setFormaPago(consulta1.get().getFormaPago());
        consulta.setDescripcionProceso(consulta1.get().getDescripcionProceso());
        consulta.setEstadoConsulta("cerrada");
        consulta.setEstaEnTratamiento(consulta1.get().isEstaEnTratamiento());

        return gateway.updateConsulta(consulta);
    }

    public Consulta tratamientoTerminado(int id) {
        Optional<Consulta> consulta1 = gateway.findById(id);

        Optional<Mascota> mascota = gatewayMasc.findById(consulta1.get().getIdMascota());

        Optional<Dueno> dueno = gatewayDueno.findById(consulta1.get().getIdDueno());

        Consulta consulta = new Consulta();

        consulta.setIdConsulta(id);
        consulta.setIdMascota(consulta1.get().getIdMascota());
        consulta.setMascota(mascota.get());
        consulta.setIdVeterinaria(consulta1.get().getIdVeterinaria());
        consulta.setVeterinaria(consulta1.get().getVeterinaria());
        consulta.setIdDueno(consulta1.get().getIdDueno());
        consulta.setDueno(dueno.get());
        consulta.setValor(consulta1.get().getValor());
        consulta.setFormaPago(consulta1.get().getFormaPago());
        consulta.setDescripcionProceso(consulta1.get().getDescripcionProceso());
        consulta.setEstadoConsulta(consulta1.get().getEstadoConsulta());
        consulta.setEstaEnTratamiento(false);

        return gateway.updateConsulta(consulta);
    }

    public Consulta tratamientoPagado(int id) {
        Optional<Consulta> consulta1 = gateway.findById(id);

        Optional<Mascota> mascota = gatewayMasc.findById(consulta1.get().getIdMascota());

        Optional<Dueno> dueno = gatewayDueno.findById(consulta1.get().getIdDueno());

        Consulta consulta = new Consulta();

        consulta.setIdConsulta(id);
        consulta.setIdMascota(consulta1.get().getIdMascota());
        consulta.setMascota(mascota.get());
        consulta.setIdVeterinaria(consulta1.get().getIdVeterinaria());
        consulta.setVeterinaria(consulta1.get().getVeterinaria());
        consulta.setIdDueno(consulta1.get().getIdDueno());
        consulta.setDueno(dueno.get());
        consulta.setValor(0);
        consulta.setFormaPago(consulta1.get().getFormaPago());
        consulta.setDescripcionProceso(consulta1.get().getDescripcionProceso());
        consulta.setEstadoConsulta(consulta1.get().getEstadoConsulta());
        consulta.setEstaEnTratamiento(consulta1.get().isEstaEnTratamiento());

        return gateway.updateConsulta(consulta);
    }

    public Consulta enTratamiento(int id) {
        Optional<Consulta> consulta1 = gateway.findById(id);

        Optional<Mascota> mascota = gatewayMasc.findById(consulta1.get().getIdMascota());

        Optional<Dueno> dueno = gatewayDueno.findById(consulta1.get().getIdDueno());

        Consulta consulta = new Consulta();

        consulta.setIdConsulta(id);
        consulta.setIdMascota(consulta1.get().getIdMascota());
        consulta.setMascota(mascota.get());
        consulta.setIdVeterinaria(consulta1.get().getIdVeterinaria());
        consulta.setVeterinaria(consulta1.get().getVeterinaria());
        consulta.setIdDueno(consulta1.get().getIdDueno());
        consulta.setDueno(dueno.get());
        consulta.setValor(consulta1.get().getValor());
        consulta.setFormaPago(consulta1.get().getFormaPago());
        consulta.setDescripcionProceso(consulta1.get().getDescripcionProceso());
        consulta.setEstadoConsulta("en tratamiento");
        consulta.setEstaEnTratamiento(true);

        return gateway.updateConsulta(consulta);
    }

    public void deleteConsulta(int id) throws ConsultOpenOrTreatmentException {
        Optional<Consulta> consulta1 = gateway.findById(id);

        String estado = consulta1.get().getEstadoConsulta();

        if(estado.equals("en tratamiento") || estado.equals("abierta")){
            Object[] args = {id};
            throw new ConsultOpenOrTreatmentException("consulta.opentreatment.message", args);
        }

        gateway.findById(id).map(consulta -> {
            gateway.deleteConsulta(consulta);
            return true;
        });
    }
}
