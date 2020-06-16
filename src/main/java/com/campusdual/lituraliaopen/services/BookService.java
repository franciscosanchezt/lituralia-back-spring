package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.model.BookDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Integer book_id) throws ResourceNotFoundException;

    List<BookDTO> getAllBooksByTitleContainingOrderByTitle(String likeTitle);

    List<BookDTO> getBooksByTitleContaining(String titleLike) throws ResourceNotFoundException;

    List<BookDTO> getBooksBySearchTerm(String searchTerm) throws ResourceNotFoundException;

    BookDTO createBook(BookDTO bookDto);

    BookDTO updateBook(BookDTO bookDto);

    BookDTO updateBook(Integer book_id, BookDTO bookDto);

    void deleteBook(BookDTO bookDto) throws ResourceNotFoundException;

    void deleteBookById(Integer book_id) throws ResourceNotFoundException;
}
