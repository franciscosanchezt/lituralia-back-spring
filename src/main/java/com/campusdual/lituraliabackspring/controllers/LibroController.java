package com.campusdual.lituraliabackspring.controllers;

import com.campusdual.lituraliabackspring.domain.Libro;
import com.campusdual.lituraliabackspring.services.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    LibroService service;

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = service.getAllLibros();
        return new ResponseEntity<List<Libro>>(libros, new HttpHeaders(), HttpStatus.OK);
    }
}
