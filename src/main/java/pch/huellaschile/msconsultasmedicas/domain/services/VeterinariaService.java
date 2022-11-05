package pch.huellaschile.msconsultasmedicas.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.domain.gateways.VeterinariaGateway;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestVeterinariaDTO;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinariaService {

    @Autowired
    private VeterinariaGateway gateway;

    public List<Veterinaria> getAll() {
        return gateway.getAll();
    }

    public Optional<Veterinaria> findById(int id) {
        return gateway.findById(id);
    }

    public Veterinaria save(Veterinaria veterinaria) {
        return gateway.saveVeterinaria(veterinaria);
    }

    public Veterinaria update(int id, RequestVeterinariaDTO dto) {

        Optional<Veterinaria> veterinaria1 = gateway.findById(id);

        if(veterinaria1.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veterinaria no existe");
        }

        Veterinaria veterinaria = new Veterinaria();

        veterinaria.setIdVeterinaria(id);
        veterinaria.setNombre(dto.getNombre());

        return gateway.updateVeterinaria(veterinaria);
    }

    public void deleteVeterinaria(int id){
        gateway.findById(id).map(veterinaria -> {
            gateway.deleteVeterinaria(veterinaria);
            return true;
        });
    }
}
