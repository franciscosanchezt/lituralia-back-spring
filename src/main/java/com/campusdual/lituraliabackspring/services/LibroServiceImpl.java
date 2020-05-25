package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.api.mapper.LibroMapper;
import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Libro;
import com.campusdual.lituraliabackspring.repositories.LibrosRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    LibroMapper mapper;
    @Autowired
    LibrosRepository repository;


    @Override
    public List<LibroDTO> getAllLibros() {
        return repository.findAll()
                         .stream()
                         .map(mapper::libroToLibroDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public LibroDTO getLibroById(Long idLibro) {
        return repository.findById(idLibro)
                         .map(mapper::libroToLibroDTO)
                         .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public LibroDTO createLibro(LibroDTO libroDto) {
        Libro entity = repository.save(mapper.libroDTOToLibro(libroDto));
        return mapper.libroToLibroDTO(entity);
    }

    @Override
    public LibroDTO updateLibro(Long idLibro, LibroDTO libroDto) {
        Libro libro = mapper.libroDTOToLibro(libroDto);
        libro.setIdLibro(idLibro);
        Libro entity = repository.save(libro);
        return mapper.libroToLibroDTO(entity);
    }

    @Override
    public LibroDTO updateLibro(LibroDTO libroDto) {
        return updateLibro(libroDto.getIdLibro(), libroDto);
    }


    @Override
    public void deleteLibro(LibroDTO libro) throws ResourceNotFoundException {
        deleteLibroById(libro.getIdLibro());
    }

    @Override
    public void deleteLibroById(Long idLibro) throws ResourceNotFoundException {
        Optional<Libro> employee = repository.findById(idLibro);

        if (employee.isPresent()) {
            repository.deleteById(idLibro);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
