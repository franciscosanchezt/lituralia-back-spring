package com.campusdual.lituraliabackspring.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import org.junit.jupiter.api.Test;

class BookMapperTest {

    LibroMapper mapper = LibroMapper.INSTANCE;

    public static final String TITLE = "Hamlet";
    public static final String ISBN = "123456";
    public static final long ID = 1L;

    @Test
    void libroToLibroDTO() {
        //given
        Book book1 = Book.builder()
                         .bookId(ID)
                         .isbn(ISBN)
                         .title(TITLE)
                         .build();

        //when
        LibroDTO libroDTO = mapper.libroToLibroDTO(book1);

        //then
        assertEquals(Long.valueOf(ID), libroDTO.getIdLibro());
        assertEquals(ISBN, libroDTO.getIsbn());
        assertEquals(TITLE, libroDTO.getTitulo());
    }

    @Test
    void libroDTOToLibro() {
        //given
        LibroDTO libroDTO = LibroDTO.builder()
                                    .idLibro(ID)
                                    .isbn(ISBN)
                                    .titulo(TITLE)
                                    .build();

        //when
        Book book = mapper.libroDTOToLibro(libroDTO);

        //then
        assertEquals(Long.valueOf(ID), book.getBookId());
        assertEquals(ISBN, book.getIsbn());
        assertEquals(TITLE, book.getTitle());
    }
}