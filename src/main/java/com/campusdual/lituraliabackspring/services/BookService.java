package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.api.model.BookDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long book_id) throws ResourceNotFoundException;

    BookDTO createBook(BookDTO bookDto);

    BookDTO updateBook(BookDTO bookDto);

    BookDTO updateBook(Long book_id, BookDTO bookDto);

    void deleteBook(BookDTO bookDto) throws ResourceNotFoundException;

    void deleteBookById(Long book_id) throws ResourceNotFoundException;
}
