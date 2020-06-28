package com.campusdual.lituraliaopen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.campusdual.lituraliaopen.api.mapper.AuthorMapper;
import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.model.AuthorService;
import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.domain.Author;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.repositories.AuthorRepository;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import java.util.Arrays;
import java.util.HashSet;
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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

class AuthorServiceImplTest {

    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    BookRepository bookRepository;

    BookMapper bookMapper = BookMapper.INSTANCE;
    AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorRepository, authorMapper,
                                              bookRepository, bookMapper);
    }

    @Test
    void getAllAuthors() {
        //given
        Author author1 = Author.builder().authorId(1).authorName("Shakespeare").build();
        Author author2 = Author.builder().authorId(1).authorName("Cervantes").build();

        Page<Author> page = new PageImpl<Author>(Arrays.asList(author1, author2));

        when(authorRepository.findAll(any(Pageable.class))).thenReturn(page);

        //when
        Page<AuthorDTO> authorDTOS = authorService.getAllAuthors(PageRequest.of(0, 10));

        //then
        assertEquals(2, authorDTOS.getSize());
        assertEquals(author1.getAuthorName(), authorDTOS.getContent().get(0).getAuthorName());
    }

    @Test
    void getAuthorsBySearchTerm() {
        //given
        Author author1 = Author.builder().authorId(1).authorName("Shakespeare").build();
        Author author2 = Author.builder().authorId(1).authorName("Cervantes").build();

        Page<Author> page = new PageImpl<Author>(Arrays.asList(author1, author2));

        when(authorRepository.findByAuthorNameContainingIgnoreCase(any(String.class), any(Pageable.class))).thenReturn(page);

        //when
        Page<AuthorDTO> authorDTOS = authorService.searchAuthors("", PageRequest.of(0, 10));

        //then
        assertEquals(2, authorDTOS.getSize());
        assertEquals(author1.getAuthorName(), authorDTOS.getContent().get(0).getAuthorName());
    }

    @Test
    void getAuthorById() {
        //given
        Author author1 = Author.builder().authorId(1).authorName("Shakespeare").build();

        when(authorRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(author1));

        //when
        AuthorDTO authorDTO = authorService.getAuthorById(1);

        //then
        assertEquals(author1.getAuthorName(), authorDTO.getAuthorName());
    }

    @Test()
    void getAuthorByIdNotFound() {
        Optional<Author> authorOptional = Optional.empty();

        when(authorRepository.findById(anyInt())).thenReturn(authorOptional);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            AuthorDTO author = authorService.getAuthorById(1);
        });
    }

    @Test
    void createAuthor() {
        //given
        AuthorDTO authorDTO = AuthorDTO.builder().authorId(1).authorName("Shakespaeare").build();

        Author savedAuthor = Author.builder().authorId(1).authorName(authorDTO.getAuthorName()).build();

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        //when
        AuthorDTO savedDto = authorService.createAuthor(authorDTO);

        //then
        assertEquals(authorDTO.getAuthorName(), savedDto.getAuthorName());
    }

    @Test
    void updateAuthor() {
        //given
        AuthorDTO authorDTO = AuthorDTO.builder().authorId(1).authorName("Shakespaeare").build();

        Author savedAuthor = Author.builder().authorId(1).authorName(authorDTO.getAuthorName()).build();

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        //when
        AuthorDTO savedDto = authorService.updateAuthor(1, authorDTO);
        AuthorDTO savedDto1 = authorService.updateAuthor(authorDTO);

        //then
        assertEquals(authorDTO.getAuthorName(), savedDto.getAuthorName());
        assertEquals(authorDTO.getAuthorName(), savedDto1.getAuthorName());
    }

    @Test
    void deleteAuthorById() {
        Integer id = 1;
        authorService.deleteAuthorById(id);
        verify(authorRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void getAuthorBooks() {
        //given
        Book book1 = Book.builder().bookId(1).title("Harry Potter 1").build();
        Book book2 = Book.builder().bookId(2).title("Harry Potter 2").build();
        Author author = Author.builder().authorId(1).authorName("Rowling")
                              .books(new HashSet<>(Arrays.asList(book1, book2))).build();

        when(authorRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(author));

        //when
        Slice<BookDTO> authorBooks = authorService.getAuthorBooks(1);

        //then
        assertEquals(2, authorBooks.getSize());
        assertEquals(book2.getTitle(), authorBooks.iterator().next().getTitle());
    }

    @Test
    void setAuthorBook() {

        //TODO
//        Book book1 = Book.builder().bookId(1).title("Harry Potter 1").authors(new HashSet<>()).build();
//        Book book2 = Book.builder().bookId(2).title("Harry Potter 2").authors(new HashSet<>()).build();
//        Author author = Author.builder().authorId(1)
//                        .authorName("Rowling")
//                        .books(new HashSet<>()).build();
//        author.getBooks().add(book1);
//        book1.getAuthors().add(author);
//
//
//        when(authorRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(author));
//        when(bookRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(book2));
//        when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(book2);
//
//        //when
//        Slice<BookDTO> authorBooks = this.authorService.setAuthorBook(1, 2);
//
//        //then
//        assertEquals(2, authorBooks.getSize());
//        Iterator<BookDTO> iterator = authorBooks.iterator();
//        iterator.next();
//
//        assertEquals(book2.getTitle(), iterator.next().getTitle());
    }

    @Test
    void deleteAuthorBook() {
    }
}