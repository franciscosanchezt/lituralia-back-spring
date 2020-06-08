package com.campusdual.lituraliabackspring.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.campusdual.lituraliabackspring.api.model.BookDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import org.junit.jupiter.api.Test;

class BookMapperTest {

    BookMapper mapper = BookMapper.INSTANCE;

    public static final String TITLE = "Hamlet";
    public static final String ISBN = "123456";
    public static final long ID = 1L;

    @Test
    void bookToBookDTO() {
        //given
        Book book1 = Book.builder()
                         .bookId(ID)
                         .isbn(ISBN)
                         .title(TITLE)
                         .build();

        //when
        BookDTO bookDTO = mapper.bookToBookDTO(book1);

        //then
        assertEquals(Long.valueOf(ID), bookDTO.getBookId());
        assertEquals(ISBN, bookDTO.getIsbn());
        assertEquals(TITLE, bookDTO.getTitle());
    }

    @Test
    void bookDTOToBook() {
        //given
        BookDTO bookDTO = BookDTO.builder()
                                 .bookId(ID)
                                 .isbn(ISBN)
                                 .title(TITLE)
                                 .build();

        //when
        Book book = mapper.bookDTOToBook(bookDTO);

        //then
        assertEquals(Long.valueOf(ID), book.getBookId());
        assertEquals(ISBN, book.getIsbn());
        assertEquals(TITLE, book.getTitle());
    }
}