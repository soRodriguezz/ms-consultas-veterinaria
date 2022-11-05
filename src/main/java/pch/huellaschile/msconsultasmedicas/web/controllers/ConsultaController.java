package pch.huellaschile.msconsultasmedicas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pch.huellaschile.msconsultasmedicas.domain.entities.Consulta;
import pch.huellaschile.msconsultasmedicas.domain.entities.Dueno;
import pch.huellaschile.msconsultasmedicas.domain.services.ConsultaService;
import pch.huellaschile.msconsultasmedicas.persistence.dto.RequestConsultaDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping("/all")
    public ResponseEntity<List<Consulta>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Consulta>> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Consulta> save(@RequestBody RequestConsultaDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Consulta> update(@PathVariable int id, @RequestBody RequestConsultaDTO dto) {
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @PatchMapping("/estado-cerrada/{id}")
    public ResponseEntity<Consulta> estadoCerrada(@PathVariable int id) {
        return new ResponseEntity<>(service.estadoCerrada(id), HttpStatus.OK);
    }

    @PatchMapping("/tratamiento-terminado/{id}")
    public ResponseEntity<Consulta> tratamientoTerminado(@PathVariable int id) {
        return new ResponseEntity<>(service.tratamientoTerminado(id), HttpStatus.OK);
    }

    @PatchMapping("/tratamiento-pagado/{id}")
    public ResponseEntity<Consulta> tratamientoPagado(@PathVariable int id) {
        return new ResponseEntity<>(service.tratamientoPagado(id), HttpStatus.OK);
    }

    @PatchMapping("/en-tratamiento/{id}")
    public ResponseEntity<Consulta> enTratamiento(@PathVariable int id) {
        return new ResponseEntity<>(service.enTratamiento(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>  deleteConsulta(@PathVariable int id) {
        service.deleteConsulta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
