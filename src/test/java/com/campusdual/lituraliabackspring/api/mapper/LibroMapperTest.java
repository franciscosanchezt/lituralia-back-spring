package com.campusdual.lituraliabackspring.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Libro;
import org.junit.jupiter.api.Test;

class LibroMapperTest {

    LibroMapper mapper = LibroMapper.INSTANCE;

    public static final String TITULO = "Hamlet";
    public static final String ISBN = "123456";
    public static final long ID = 1L;

    @Test
    void libroToLibroDTO() {
        //given
        Libro libro1 = Libro.builder()
                            .idLibro(ID)
                            .isbn(ISBN)
                            .titulo(TITULO)
                            .build();

        //when
        LibroDTO libroDTO = mapper.libroToLibroDTO(libro1);

        //then
        assertEquals(Long.valueOf(ID), libroDTO.getIdLibro());
        assertEquals(ISBN, libroDTO.getIsbn());
        assertEquals(TITULO, libroDTO.getTitulo());
    }

    @Test
    void libroDTOToLibro() {
        //given
        LibroDTO libroDTO = LibroDTO.builder()
                                    .idLibro(ID)
                                    .isbn(ISBN)
                                    .titulo(TITULO)
                                    .build();

        //when
        Libro libro = mapper.libroDTOToLibro(libroDTO);

        //then
        assertEquals(Long.valueOf(ID), libro.getIdLibro());
        assertEquals(ISBN, libro.getIsbn());
        assertEquals(TITULO, libro.getTitulo());
    }
}