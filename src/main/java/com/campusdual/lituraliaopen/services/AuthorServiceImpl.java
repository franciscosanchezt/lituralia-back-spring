package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.AuthorMapper;
import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.mapper.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.mapper.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.service.AuthorService;
import com.campusdual.lituraliaopen.domain.Author;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.repositories.AuthorRepository;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper,
                             BookRepository bookRepository, BookMapper bookMapper) {
        this.authorMapper     = authorMapper;
        this.authorRepository = authorRepository;
        this.bookRepository   = bookRepository;
        this.bookMapper       = bookMapper;
    }

    @Override
    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable)
                               .map(authorMapper::authorToAuthorDTO);
    }

    @Override
    public Page<AuthorDTO> searchAuthors(String searchTerm, Pageable pageable) throws ResourceNotFoundException {
        return authorRepository.findByAuthorNameContainingIgnoreCase(searchTerm, pageable)
                               .map(authorMapper::authorToAuthorDTO);
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
    public Slice<BookDTO> getAuthorBooks(Integer authorId) throws ResourceNotFoundException {
        return new SliceImpl<>(authorRepository.findById(authorId)
                                               .map(author -> {
                                                   return author.getBooks().stream()
                                                                .map(bookMapper::bookToBookDTO)
                                                                .collect(Collectors.toList());
                                               })
                                               .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Slice<BookDTO> setAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException {
        return new SliceImpl<>(authorRepository.findById(authorId)
                                               .map(author -> {
                                                   Book book = bookRepository.findById(bookId)
                                                                             .orElseThrow(ResourceNotFoundException::new);
                                                   book.getAuthors().add(author);
                                                   bookRepository.saveAndFlush(book);//TODO: check if added book ins in returned slice
                                                   return author.getBooks().stream()
                                                                .map(bookMapper::bookToBookDTO)
                                                                .collect(Collectors.toList());
                                               })
                                               .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Slice<BookDTO> deleteAuthorBook(Integer authorId, Integer bookId) throws ResourceNotFoundException {
        return new SliceImpl<>(authorRepository.findById(authorId)
                                               .map(author -> {
                                                   Book book = bookRepository.findById(bookId)
                                                                             .orElseThrow(ResourceNotFoundException::new);
                                                   book.getAuthors().remove(author);
                                                   bookRepository.saveAndFlush(book);
                                                   return author.getBooks().stream()
                                                                .map(bookMapper::bookToBookDTO)
                                                                .collect(Collectors.toList());
                                               })
                                               .orElseThrow(ResourceNotFoundException::new));
    }
}
