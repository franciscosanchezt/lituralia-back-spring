package com.campusdual.lituraliaopen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    PublisherRepository publisherRepository;
    @Mock
    GenreRepository genreRepository;
    @Mock
    AuthorRepository authorRepository;

    BookMapper bookMapper = BookMapper.INSTANCE;
    PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
    GenreMapper genreMapper = GenreMapper.INSTANCE;
    AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(bookRepository, bookMapper,
                                          publisherRepository, publisherMapper,
                                          genreRepository, genreMapper,
                                          authorRepository, authorMapper);
    }

    @Test
    void getAllBooks() {
        //given
        Book book1 = Book.builder()
                         .bookId(1)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        Book book2 = Book.builder()
                         .bookId(2)
                         .isbn("123457")
                         .title("MacBeth")
                         .build();

        Page<Book> page = new PageImpl<Book>(Arrays.asList(book1, book2));

        when(bookRepository.findAll(any(Pageable.class))).thenReturn(page);

        //when
        Page<BookDTO> bookDTOS = bookService.getAllBooks(PageRequest.of(10, 5));

        //then
        assertEquals(2, bookDTOS.getContent().size());
        assertEquals("Hamlet", bookDTOS.getContent().get(0).getTitle());
    }


    @Test
    void searchBooks() {
        //given
        Book book1 = Book.builder()
                         .bookId(1)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        Book book2 = Book.builder()
                         .bookId(2)
                         .isbn("123457")
                         .title("MacBeth")
                         .build();

        Page<Book> page = new PageImpl<Book>(Arrays.asList(book1, book2));

        when(bookRepository.findByTitleContainingIgnoreCase(any(String.class), any(Pageable.class))).thenReturn(page);

        //when
        Page<BookDTO> bookDTOS = bookService.searchBooks("", PageRequest.of(10, 5));

        //then
        assertEquals(2, bookDTOS.getContent().size());
        assertEquals("Hamlet", bookDTOS.getContent().get(0).getTitle());
    }


    @Test
    void getBookById() {
        //given
        Book book1 = Book.builder()
                         .bookId(1)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        when(bookRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(book1));

        //when
        BookDTO bookDTO = bookService.getBookById(1);

        //then
        assertEquals("Hamlet", bookDTO.getTitle());

    }

    @Test()
    void getBookByIdNotFound() {
        Optional<Book> bookOptional = Optional.empty();

        when(bookRepository.findById(anyInt())).thenReturn(bookOptional);

        Assertions.assertThrows(Exception.class, () -> {
            BookDTO book = bookService.getBookById(1);
        });
    }

    @Test
    void createBook() {
        //given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Hamlet");

        Book savedBook = new Book();
        savedBook.setTitle(bookDTO.getTitle());
        savedBook.setBookId(1);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //when
        BookDTO savedDto = bookService.createBook(bookDTO);

        //then
        assertEquals(bookDTO.getTitle(), savedDto.getTitle());
    }

    @Test
    void updateBook() {
        //given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Hamlet");

        Book savedBook = new Book();
        savedBook.setTitle(bookDTO.getTitle());
        savedBook.setBookId(1);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //when
        BookDTO savedDto = bookService.updateBook(1, bookDTO);
        BookDTO savedDto1 = bookService.updateBook(bookDTO);

        //then
        assertEquals(bookDTO.getTitle(), savedDto.getTitle());
        assertEquals(bookDTO.getTitle(), savedDto1.getTitle());
    }

    @Test
    void deleteBookById() {
        Integer id = 1;
        bookService.deleteBookById(id);
        verify(bookRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void getBookPublisher() {
        Publisher publisher = Publisher.builder().publisherId(1).publisherName("Salamandra").build();
        Book book = Book.builder().bookId(1).title("Harry Potter").publisher(publisher).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));

        //when
        PublisherDTO bookPublisher = this.bookService.getBookPublisher(1);

        //then
        assertEquals("Salamandra", bookPublisher.getPublisherName());
    }

    @Test
    void setBookPublisher() {
        Publisher publisher = Publisher.builder().publisherId(1).publisherName("Salamandra").build();
        Publisher publisher2 = Publisher.builder().publisherId(2).publisherName("Mundo").build();
        Book book = Book.builder().bookId(1).title("Harry Potter").publisher(publisher).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.of(book));
        when(publisherRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(publisher2));
        when(publisherRepository.saveAndFlush(any(Publisher.class))).thenReturn(publisher2);
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        //when
        PublisherDTO publisherDTO = this.bookService.setBookPublisher(1, 2);

        //then
        assertEquals(publisher2.getPublisherName(), publisherDTO.getPublisherName());
    }


    @Test
    void getBookGenre() {
        Genre genre1 = Genre.builder().genreId(1).genreName("Terror").build();
        Genre genre2 = Genre.builder().genreId(2).genreName("Comedy").build();
        Book book = Book.builder().bookId(1)
                        .title("Harry Potter")
                        .genres(new HashSet<>(Arrays.asList(genre1, genre2))).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));

        //when
        Slice<GenreDTO> bookGenres = this.bookService.getBookGenres(1);

        //then
        assertEquals(2, bookGenres.getSize());
        assertEquals(genre1.getGenreName(), bookGenres.iterator().next().getGenreName());
    }


    @Test
    void setBookGenre() {
        Genre genre1 = Genre.builder().genreId(1).genreName("Terror").build();
        Genre genre2 = Genre.builder().genreId(2).genreName("Comedy").build();
        Book book = Book.builder().bookId(1)
                        .title("Harry Potter")
                        .genres(new HashSet<>(Collections.singletonList(genre1))).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));
        when(genreRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(genre2));
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        //when
        Slice<GenreDTO> bookGenres = this.bookService.setBookGenre(1, 2);

        //then
        assertEquals(2, bookGenres.getSize());
        Iterator<GenreDTO> iterator = bookGenres.iterator();
        iterator.next();

        assertEquals(genre2.getGenreName(), iterator.next().getGenreName());
    }

    @Test
    void deleteBookGenre() {
        Genre genre1 = Genre.builder().genreId(1).genreName("Terror").build();
        Genre genre2 = Genre.builder().genreId(2).genreName("Comedy").build();
        Book book = Book.builder().bookId(1)
                        .title("Harry Potter")
                        .genres(new HashSet<>(Arrays.asList(genre1, genre2))).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));
        when(genreRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(genre1));
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        //when
        Slice<GenreDTO> bookGenres = this.bookService.deleteBookGenre(1, 1);

        //then
        assertEquals(1, bookGenres.getSize());
        assertEquals(genre2.getGenreName(), bookGenres.iterator().next().getGenreName());
    }


    @Test
    void getBookAuthor() {
        Author author1 = Author.builder().authorId(1).authorName("Shakespeare").build();
        Author author2 = Author.builder().authorId(2).authorName("Cervantes").build();
        Book book = Book.builder().bookId(1)
                        .title("Harry Potter")
                        .authors(new HashSet<>(Arrays.asList(author1, author2))).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));

        //when
        Slice<AuthorDTO> bookAuthors = this.bookService.getBookAuthors(1);

        //then
        assertEquals(2, bookAuthors.getSize());
        assertEquals(author1.getAuthorName(), bookAuthors.iterator().next().getAuthorName());
    }


    @Test
    void setBookAuthor() {
        Author author1 = Author.builder().authorId(1).authorName("Shakespeare").build();
        Author author2 = Author.builder().authorId(2).authorName("Cervantes").build();
        Book book = Book.builder().bookId(1)
                        .title("Harry Potter")
                        .authors(new HashSet<>(Collections.singletonList(author1))).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));
        when(authorRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(author2));
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        //when
        Slice<AuthorDTO> bookAuthors = this.bookService.setBookAuthor(1, 2);

        //then
        assertEquals(2, bookAuthors.getSize());
        assertEquals(author2.getAuthorName(), bookAuthors.getContent().get(1).getAuthorName());
    }

    @Test
    void deleteBookAuthor() {
        Author author1 = Author.builder().authorId(1).authorName("Shakespeare").build();
        Author author2 = Author.builder().authorId(2).authorName("Cervantes").build();
        Book book = Book.builder().bookId(1)
                        .title("Harry Potter")
                        .authors(new HashSet<>(Arrays.asList(author1, author2))).build();

        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book));
        when(authorRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(author1));
        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book);

        //when
        Slice<AuthorDTO> bookAuthors = this.bookService.deleteBookAuthor(1, 1);

        //then
        assertEquals(1, bookAuthors.getSize());
        assertEquals(author2.getAuthorName(), bookAuthors.iterator().next().getAuthorName());
    }

}