package pch.huellaschile.msconsultasmedicas.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.gateways.DuenoGateway;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestDuenoDTO;

import java.util.List;
import java.util.Optional;

@Service
public class DuenoService {

    @Autowired
    private DuenoGateway gateway;

    public List<Dueno> getAll() {
        return gateway.getAll();
    }

    public Optional<Dueno> findById(int id) {
        return gateway.findById(id);
    }

    public Dueno save(Dueno dueno) {
        return gateway.saveDueno(dueno);
    }

    public Dueno update(int id, RequestDuenoDTO dto) {
        Dueno dueno = new Dueno();

        Optional<Dueno> dueno1 = gateway.findById(id);

        if(dueno1.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dueño no existe");
        }

        dueno.setIdDueno(id);
        dueno.setNombre(dto.getNombre());

        return gateway.updateDueno(dueno);
    }

    public void deleteDueno(int id){
        gateway.findById(id).map(dueno -> {
            gateway.deleteDueno(dueno);
            return true;
        });
    }
}
