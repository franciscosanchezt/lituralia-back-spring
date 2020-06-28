package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface AuthorService {

    Page<AuthorDTO> getAllAuthors(Pageable pageable);

    AuthorDTO getAuthorById(Integer author_id) throws ResourceNotFoundException;

    AuthorDTO createAuthor(AuthorDTO authorDto);

    AuthorDTO updateAuthor(AuthorDTO authorDto);

    AuthorDTO updateAuthor(Integer author_id, AuthorDTO authorDto);

    void deleteAuthorById(Integer author_id) throws ResourceNotFoundException;

    Page<AuthorDTO> searchAuthors(String searchTerm, Pageable pageable) throws ResourceNotFoundException;

    // -------- Author's Books

    Slice<BookDTO> getAuthorBooks(Integer authorId) throws ResourceNotFoundException;

    Slice<BookDTO> setAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException;

    Slice<BookDTO> deleteAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException;
}
