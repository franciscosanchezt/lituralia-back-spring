package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Integer book_id) throws ResourceNotFoundException;

    BookDTO createBook(BookDTO bookDto);

    BookDTO updateBook(BookDTO bookDto);

    BookDTO updateBook(Integer book_id, BookDTO bookDto);

    void deleteBookById(Integer book_id) throws ResourceNotFoundException;

    List<BookDTO> getBooksBySearchTerm(String searchTerm) throws ResourceNotFoundException;
}
