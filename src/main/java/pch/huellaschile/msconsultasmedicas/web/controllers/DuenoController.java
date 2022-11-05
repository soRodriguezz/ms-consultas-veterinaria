package pch.huellaschile.msconsultasmedicas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.services.DuenoService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestDuenoDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dueno")
public class DuenoController {
    @Autowired
    private DuenoService service;

    @GetMapping("/all")
    public ResponseEntity<List<Dueno>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dueno>> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Dueno> save(@RequestBody Dueno dueno) {
        return new ResponseEntity<>(service.save(dueno), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dueno> update(@PathVariable int id, @RequestBody RequestDuenoDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        service.deleteDueno(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
