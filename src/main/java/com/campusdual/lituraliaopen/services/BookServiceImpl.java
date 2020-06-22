package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.AuthorMapper;
import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.mapper.GenreMapper;
import com.campusdual.lituraliaopen.api.mapper.PublisherMapper;
import com.campusdual.lituraliaopen.api.model.BookService;
import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import com.campusdual.lituraliaopen.domain.Author;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.domain.Genre;
import com.campusdual.lituraliaopen.domain.Publisher;
import com.campusdual.lituraliaopen.repositories.AuthorRepository;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import com.campusdual.lituraliaopen.repositories.GenreRepository;
import com.campusdual.lituraliaopen.repositories.PublisherRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper,
                           PublisherRepository publisherRepository, PublisherMapper publisherMapper,
                           GenreRepository genreRepository, GenreMapper genreMapper,
                           AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.bookRepository      = bookRepository;
        this.publisherRepository = publisherRepository;
        this.bookMapper          = bookMapper;
        this.publisherMapper     = publisherMapper;
        this.genreRepository     = genreRepository;
        this.genreMapper         = genreMapper;
        this.authorRepository    = authorRepository;
        this.authorMapper        = authorMapper;
    }


    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                             .stream()
                             .map(bookMapper::bookToBookDTO)
                             .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksBySearchTerm(String searchTerm) throws ResourceNotFoundException {
        return bookRepository.findBySearchTerm(searchTerm)
                             .stream()
                             .map(bookMapper::bookToBookDTO)
                             .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                             .map(bookMapper::bookToBookDTO)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO createBook(BookDTO bookDto) {
        Book entity = bookRepository.save(bookMapper.bookDTOToBook(bookDto));
        return bookMapper.bookToBookDTO(entity);
    }

    @Override
    public BookDTO updateBook(Integer bookId, BookDTO bookDto) {
        Book book = bookMapper.bookDTOToBook(bookDto);
        book.setBookId(bookId);
        Book entity = bookRepository.save(book);
        return bookMapper.bookToBookDTO(entity);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDto) {
        return updateBook(bookDto.getBookId(), bookDto);
    }

    @Override
    public void deleteBookById(Integer bookId) throws ResourceNotFoundException {
        bookRepository.deleteById(bookId);
    }

    // -------- Book's Publisher


    @Override
    public PublisherDTO getBookPublisher(Integer bookId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(Book::getPublisher)
                             .map(publisherMapper::publisherToPublisherDTO)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO setBookPublisher(Integer bookId, Integer publisherId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 Publisher publisher = publisherRepository.findById(publisherId)
                                                                          .orElseThrow(ResourceNotFoundException::new);
                                 book.setPublisher(publisher);
                                 return bookRepository.saveAndFlush(book);
                             })
                             .map(bookMapper::bookToBookDTO)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    // -------- Book's Genres

    @Override
    public Set<GenreDTO> getBookGenres(Integer bookId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 return book.getGenres().stream()
                                            .map(genreMapper::genreToGenreDTO)
                                            .collect(Collectors.toSet());
                             })
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO setBookGenre(Integer bookId, Integer genreId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 Genre genre = genreRepository.findById(genreId)
                                                              .orElseThrow(ResourceNotFoundException::new);
                                 book.getGenres().add(genre);
                                 return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                             })
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO deleteBookGenre(Integer bookId, Integer genreId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 Genre genre = genreRepository.findById(genreId)
                                                              .orElseThrow(ResourceNotFoundException::new);
                                 book.getGenres().remove(genre);
                                 return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                             })
                             .orElseThrow(ResourceNotFoundException::new);
    }

    // -------- Book's Authors


    @Override
    public Set<AuthorDTO> getBookAuthors(Integer bookId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 return book.getAuthors().stream()
                                            .map(authorMapper::authorToAuthorDTO)
                                            .collect(Collectors.toSet());
                             })
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO setBookAuthor(Integer bookId, Integer authorId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 Author author = authorRepository.findById(authorId)
                                                                 .orElseThrow(ResourceNotFoundException::new);
                                 book.getAuthors().add(author);
                                 return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                             })
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO deleteBookAuthor(Integer bookId, Integer authorId) throws ResourceNotFoundException {
        return bookRepository.findById(bookId)
                             .map(book -> {
                                 Author author = authorRepository.findById(authorId)
                                                                 .orElseThrow(ResourceNotFoundException::new);
                                 book.getAuthors().remove(author);
                                 return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                             })
                             .orElseThrow(ResourceNotFoundException::new);
    }
}
