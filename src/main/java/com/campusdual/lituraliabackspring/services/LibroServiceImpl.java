package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.domain.Libro;
import com.campusdual.lituraliabackspring.exception.RecordNotFoundException;
import com.campusdual.lituraliabackspring.repositories.LibrosRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    LibrosRepository repository;


    @Override
    public List<Libro> getAllLibros() {
        List<Libro> libros = repository.findAll();

        if (libros.size() > 0) {
            return libros;
        } else {
            return new ArrayList<Libro>();
        }
    }

    @Override
    public Libro getLibroById(Long idLibro) throws RecordNotFoundException {
        Optional<Libro> libro = repository.findById(idLibro);

        if (libro.isPresent()) {
            return libro.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    @Override
    public Libro createOrUpdateLibro(Libro libro) {
        Optional<Libro> employee = repository.findById(libro.getIdLibro());
        if (employee.isPresent()) {
            Libro newEntity = employee.get();
            newEntity.setIsbn(libro.getIsbn());
            newEntity.setTitulo(libro.getTitulo());
            newEntity.setSinopsis(libro.getSinopsis());
            newEntity.setFechaPublicacion(libro.getFechaPublicacion());
            newEntity.setPortada(libro.getPortada());
            newEntity.setIdEditorial(libro.getIdEditorial());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            libro = repository.save(libro);
            return libro;
        }
    }


    @Override
    public void deleteLibro(Libro libro) throws RecordNotFoundException {
        deleteLibroById(libro.getIdLibro());
    }

    @Override
    public void deleteLibroById(Long idLibro) throws RecordNotFoundException {
        Optional<Libro> employee = repository.findById(idLibro);

        if (employee.isPresent()) {
            repository.deleteById(idLibro);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
