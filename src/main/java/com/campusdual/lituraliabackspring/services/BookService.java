package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface BookService {

    List<LibroDTO> getAllBooks();

    LibroDTO getBookById(Long book_id) throws ResourceNotFoundException;

    LibroDTO createBook(LibroDTO libroDto);

    LibroDTO updateBook(LibroDTO libroDto);

    LibroDTO updateBook(Long book_id, LibroDTO libroDto);

    void deleteBook(LibroDTO libroDto) throws ResourceNotFoundException;

    void deleteBookById(Long book_id) throws ResourceNotFoundException;
}
