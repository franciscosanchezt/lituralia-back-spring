package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import java.util.List;
import java.util.Set;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface AuthorService {

    List<AuthorDTO> getAllAuthors();

    AuthorDTO getAuthorById(Integer author_id) throws ResourceNotFoundException;

    AuthorDTO createAuthor(AuthorDTO authorDto);

    AuthorDTO updateAuthor(AuthorDTO authorDto);

    AuthorDTO updateAuthor(Integer author_id, AuthorDTO authorDto);

    void deleteAuthorById(Integer author_id) throws ResourceNotFoundException;

    List<AuthorDTO> getAuthorsBySearchTerm(String searchTerm) throws ResourceNotFoundException;

    // -------- Author's Books

    Set<BookDTO> getAuthorBooks(Integer authorId) throws ResourceNotFoundException;

    BookDTO setAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException;

    BookDTO deleteAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException;
}
