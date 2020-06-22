package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.AuthorMapper;
import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.model.AuthorService;
import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.domain.Author;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.repositories.AuthorRepository;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository,
                             BookRepository bookRepository, BookMapper bookMapper) {
        this.authorMapper     = authorMapper;
        this.authorRepository = authorRepository;
        this.bookRepository   = bookRepository;
        this.bookMapper       = bookMapper;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                               .stream()
                               .map(authorMapper::authorToAuthorDTO)
                               .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDTO> getAuthorsBySearchTerm(String searchTerm) throws ResourceNotFoundException {
        return authorRepository.findBySearchTerm(searchTerm)
                               .stream()
                               .map(authorMapper::authorToAuthorDTO)
                               .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(Integer author_id) {
        return authorRepository.findById(author_id)
                               .map(authorMapper::authorToAuthorDTO)
                               .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDto) {
        Author entity = authorRepository.save(authorMapper.authorDTOToAuthor(authorDto));
        return authorMapper.authorToAuthorDTO(entity);
    }

    @Override
    public AuthorDTO updateAuthor(Integer author_id, AuthorDTO authorDto) {
        Author author = authorMapper.authorDTOToAuthor(authorDto);
        author.setAuthorId(author_id);
        Author entity = authorRepository.save(author);
        return authorMapper.authorToAuthorDTO(entity);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDto) {
        return updateAuthor(authorDto.getAuthorId(), authorDto);
    }

    @Override
    public void deleteAuthorById(Integer author_id) throws ResourceNotFoundException {
        authorRepository.deleteById(author_id);
    }

    // -------- Author's Books

    @Override
    public Set<BookDTO> getAuthorBooks(Integer authorId) throws ResourceNotFoundException {
        return authorRepository.findById(authorId)
                               .map(author -> {
                                   return author.getBooks().stream()
                                                .map(bookMapper::bookToBookDTO)
                                                .collect(Collectors.toSet());
                               })
                               .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    @Override
    public BookDTO setAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException {
        return authorRepository.findById(authorId)
                               .map(author -> {
                                   Book book = bookRepository.findById(bookId)
                                                             .orElseThrow(ResourceNotFoundException::new);
                                   book.getAuthors().add(author);
                                   return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                               })
                               .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    @Override
    public BookDTO deleteAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException {
        return authorRepository.findById(authorId)
                               .map(author -> {
                                   Book book = bookRepository.findById(bookId)
                                                             .orElseThrow(ResourceNotFoundException::new);
                                   book.getAuthors().remove(author);
                                   return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                               })
                               .orElseThrow(ResourceNotFoundException::new);
    }
}
