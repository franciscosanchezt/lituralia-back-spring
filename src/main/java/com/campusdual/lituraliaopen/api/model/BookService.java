package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface BookService {

    Page<BookDTO> getAllBooks(Pageable pageable);

    BookDTO getBookById(Integer bookId) throws ResourceNotFoundException;

    BookDTO createBook(BookDTO bookDto);

    BookDTO updateBook(BookDTO bookDto);

    BookDTO updateBook(Integer bookId, BookDTO bookDto);

    void deleteBookById(Integer bookId) throws ResourceNotFoundException;

    Page<BookDTO> searchBooks(String searchTerm, Pageable pageable) throws ResourceNotFoundException;

    // -------- Book's Publisher

    PublisherDTO getBookPublisher(Integer bookId) throws ResourceNotFoundException;

    PublisherDTO setBookPublisher(Integer bookId, Integer publisherId) throws ResourceNotFoundException;

    // -------- Book's Genres

    Slice<GenreDTO> getBookGenres(Integer bookId) throws ResourceNotFoundException;

    Slice<GenreDTO> setBookGenre(Integer bookId, Integer genreId) throws ResourceNotFoundException;

    Slice<GenreDTO> deleteBookGenre(Integer bookId, Integer genreId) throws ResourceNotFoundException;

    // -------- Book's Authors

    Slice<AuthorDTO> getBookAuthors(Integer bookId) throws ResourceNotFoundException;

    Slice<AuthorDTO> setBookAuthor(Integer bookId, Integer authorId) throws ResourceNotFoundException;

    Slice<AuthorDTO> deleteBookAuthor(Integer bookId, Integer authorId) throws ResourceNotFoundException;
}
