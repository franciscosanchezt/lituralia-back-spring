package com.campusdual.lituraliabackspring.controllers;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.services.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    LibroService service;

    @GetMapping
    public ResponseEntity<List<LibroDTO>> getAllLibros() {
        List<LibroDTO> libros = service.getAllLibros();
        return new ResponseEntity<List<LibroDTO>>(libros, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> getEmployeeById(@PathVariable("id") Long id)
        throws ResourceNotFoundException {
        LibroDTO entity = service.getLibroById(id);
        return new ResponseEntity<LibroDTO>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibroDTO createLibro(@RequestBody LibroDTO libroDto) {
        return service.createLibro(libroDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public LibroDTO updateLibro(@PathVariable Long id, @RequestBody LibroDTO customerDTO) {
        return service.updateLibro(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteLibro(@PathVariable Long id) {
        service.deleteLibroById(id);
    }
}
