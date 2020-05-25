package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface LibroService {

    List<LibroDTO> getAllLibros();

    LibroDTO getLibroById(Long idLibro) throws ResourceNotFoundException;

    LibroDTO createLibro(LibroDTO libroDto);

    LibroDTO updateLibro(LibroDTO libroDto);

    LibroDTO updateLibro(Long idLibro, LibroDTO libroDto);

    void deleteLibro(LibroDTO libroDto) throws ResourceNotFoundException;

    void deleteLibroById(Long idLibro) throws ResourceNotFoundException;
}
