package pch.huellaschile.msconsultasmedicas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pch.huellaschile.msconsultasmedicas.domain.entities.Veterinaria;
import pch.huellaschile.msconsultasmedicas.domain.services.VeterinariaService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestVeterinariaDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veterinaria")
public class VeterinariaController {
    @Autowired
    private VeterinariaService service;

    @GetMapping("/all")
    public ResponseEntity<List<Veterinaria>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veterinaria>> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Veterinaria> save(@RequestBody Veterinaria veterinaria) {
        return new ResponseEntity<>(service.save(veterinaria), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Veterinaria> update(@PathVariable int id, @RequestBody RequestVeterinariaDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        service.deleteVeterinaria(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
