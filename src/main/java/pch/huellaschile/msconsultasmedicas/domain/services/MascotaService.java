package pch.huellaschile.msconsultasmedicas.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.domain.gateways.MascotaGateway;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestMascotaDTO;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaGateway gateway;

    public List<Mascota> getAll() {
        return gateway.getAll();
    }

    public Optional<Mascota> findById(int id) {
        return gateway.findById(id);
    }

    public Mascota save(Mascota mascota) {
        return gateway.saveMascota(mascota);
    }

    public Mascota update(int id, RequestMascotaDTO dto) {
        Mascota mascota = new Mascota();

        Optional<Mascota> mascota1 = gateway.findById(id);

        if(mascota1.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mascota no existe");
        }

        mascota.setIdMascota(id);
        mascota.setNombre(dto.getNombre());

        return gateway.updateMascota(mascota);
    }

    public void deleteMascota(int id){
        gateway.findById(id).map(mascota -> {
            gateway.deleteMascota(mascota);
            return true;
        });
    }
}
