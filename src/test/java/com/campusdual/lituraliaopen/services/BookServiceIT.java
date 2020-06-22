package com.campusdual.lituraliaopen.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.model.BookService;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceIT {

    public static final String TITLE = "HAMLET";
    @Autowired
    BookService service;

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper bookMapper;

    @Transactional
    @Test
    void updateBook() {
        //given
        Iterable<Book> books = repository.findAll();
        Book book = books.iterator().next();
        BookDTO bookDTO = bookMapper.bookToBookDTO(book);

        //when
        bookDTO.setTitle(TITLE);
        BookDTO bookGuardado = service.updateBook(bookDTO);

        //then
        assertEquals(TITLE, bookGuardado.getTitle());
        assertEquals(book.getIsbn(), bookGuardado.getIsbn());
    }
}