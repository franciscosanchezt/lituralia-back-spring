package com.campusdual.lituraliabackspring.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.campusdual.lituraliabackspring.api.mapper.LibroMapper;
import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import com.campusdual.lituraliabackspring.repositories.BookRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceIT {

    public static final String TITULO = "HAMLET";
    @Autowired
    BookService service;

    @Autowired
    BookRepository repository;

    @Autowired
    LibroMapper mapper;

    @Transactional
    @Test
    void updateLibro() {
        //given
        Iterable<Book> libros = repository.findAll();
        Book book = libros.iterator().next();
        LibroDTO libroDTO = mapper.libroToLibroDTO(book);

        //when
        libroDTO.setTitulo(TITULO);
        LibroDTO libroGuardado = service.updateBook(libroDTO);

        //then
        assertEquals(TITULO, libroGuardado.getTitulo());
        assertEquals(book.getIsbn(), libroGuardado.getIsbn());
    }
}