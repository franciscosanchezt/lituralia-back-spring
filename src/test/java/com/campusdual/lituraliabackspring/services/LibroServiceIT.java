package com.campusdual.lituraliabackspring.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.campusdual.lituraliabackspring.api.mapper.LibroMapper;
import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Libro;
import com.campusdual.lituraliabackspring.repositories.LibrosRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibroServiceIT {

    public static final String TITULO = "HAMLET";
    @Autowired
    LibroService service;

    @Autowired
    LibrosRepository repository;

    @Autowired
    LibroMapper mapper;

    @Transactional
    @Test
    void updateLibro() {
        //given
        Iterable<Libro> libros = repository.findAll();
        Libro libro = libros.iterator().next();
        LibroDTO libroDTO = mapper.libroToLibroDTO(libro);

        //when
        libroDTO.setTitulo(TITULO);
        LibroDTO libroGuardado = service.updateLibro(libroDTO);

        //then
        assertEquals(TITULO, libroGuardado.getTitulo());
        assertEquals(libro.getIsbn(), libroGuardado.getIsbn());
    }
}