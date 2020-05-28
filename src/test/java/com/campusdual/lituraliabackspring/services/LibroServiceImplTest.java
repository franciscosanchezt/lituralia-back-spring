package com.campusdual.lituraliabackspring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.campusdual.lituraliabackspring.api.mapper.LibroMapper;
import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Libro;
import com.campusdual.lituraliabackspring.repositories.LibrosRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LibroServiceImplTest {

    @Mock
    LibrosRepository repository;

    LibroMapper mapper = LibroMapper.INSTANCE;

    LibroService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new LibroServiceImpl(mapper, repository);
    }

    @Test
    void getAllLibros() {
        //given
        Libro libro1 = Libro.builder()
                            .idLibro(1L)
                            .isbn("123456")
                            .titulo("Hamlet")
                            .build();
        Libro libro2 = Libro.builder()
                            .idLibro(2L)
                            .isbn("123457")
                            .titulo("MacBeth")
                            .build();

        when(repository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        //when
        List<LibroDTO> libroDTOs = service.getAllLibros();

        //then
        assertEquals(2, libroDTOs.size());
    }

    @Test
    void getLibroById() {
        //given
        Libro libro1 = Libro.builder()
                            .idLibro(1L)
                            .isbn("123456")
                            .titulo("Hamlet")
                            .build();
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(libro1));

        //when
        LibroDTO libroDTO = service.getLibroById(1L);

        //then
        assertEquals("Hamlet", libroDTO.getTitulo());
    }

    @Test
    void createLibro() {
        //given
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setTitulo("Hamlet");

        Libro savedLibro = new Libro();
        savedLibro.setTitulo(libroDTO.getTitulo());
        savedLibro.setIdLibro(1L);

        when(repository.save(any(Libro.class))).thenReturn(savedLibro);

        //when
        LibroDTO savedDto = service.createLibro(libroDTO);

        //then
        assertEquals(libroDTO.getTitulo(), savedDto.getTitulo());
    }

    @Test
    void updateLibro() {
        //given
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setTitulo("Hamlet");

        Libro savedLibro = new Libro();
        savedLibro.setTitulo(libroDTO.getTitulo());
        savedLibro.setIdLibro(1L);

        when(repository.save(any(Libro.class))).thenReturn(savedLibro);

        //when
        LibroDTO savedDto = service.updateLibro(1L, libroDTO);
        LibroDTO savedDto1 = service.updateLibro(libroDTO);

        //then
        assertEquals(libroDTO.getTitulo(), savedDto.getTitulo());
        assertEquals(libroDTO.getTitulo(), savedDto1.getTitulo());
    }

    @Test
    void deleteLibroById() {
        Long id = 1L;
        service.deleteLibroById(id);
        verify(repository, times(1)).deleteById(anyLong());
    }
}