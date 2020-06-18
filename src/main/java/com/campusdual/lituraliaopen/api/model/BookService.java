package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Integer bookId) throws ResourceNotFoundException;

    BookDTO createBook(BookDTO bookDto);

    BookDTO updateBook(BookDTO bookDto);

    BookDTO updateBook(Integer bookId, BookDTO bookDto);

    void deleteBookById(Integer bookId) throws ResourceNotFoundException;

    List<BookDTO> getBooksBySearchTerm(String searchTerm) throws ResourceNotFoundException;

    PublisherDTO getBookPublisher(Integer bookId) throws ResourceNotFoundException;

    BookDTO setBookPublisher(Integer bookId, Integer publisherId) throws ResourceNotFoundException;

    List<GenreDTO> getBookGenres(Integer bookId) throws ResourceNotFoundException;

    BookDTO setBookGenre(Integer bookId, Integer genreId) throws ResourceNotFoundException;

    BookDTO deleteBookGenre(Integer bookId, Integer genreId) throws ResourceNotFoundException;
}
