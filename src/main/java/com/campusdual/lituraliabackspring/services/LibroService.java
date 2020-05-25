package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.domain.Libro;
import com.campusdual.lituraliabackspring.exception.RecordNotFoundException;
import java.util.List;

public interface LibroService {

    List<Libro> getAllLibros();

    Libro getLibroById(Long idLibro) throws RecordNotFoundException;

    Libro createOrUpdateLibro(Libro libro);

    void deleteLibro(Libro libro) throws RecordNotFoundException;

    void deleteLibroById(Long idLibro) throws RecordNotFoundException;
}
