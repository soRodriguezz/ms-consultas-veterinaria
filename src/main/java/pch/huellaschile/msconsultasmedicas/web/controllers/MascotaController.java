package pch.huellaschile.msconsultasmedicas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pch.huellaschile.msconsultasmedicas.domain.entities.Mascota;
import pch.huellaschile.msconsultasmedicas.domain.services.MascotaService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestMascotaDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mascota")
public class MascotaController {
    @Autowired
    private MascotaService service;

    @GetMapping("/all")
    public ResponseEntity<List<Mascota>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Mascota>> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Mascota> save(@RequestBody Mascota mascota) {
        return new ResponseEntity<>(service.save(mascota), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mascota> update(@PathVariable int id, @RequestBody RequestMascotaDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        service.deleteMascota(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
