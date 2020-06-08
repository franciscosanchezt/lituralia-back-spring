package com.campusdual.lituraliabackspring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.campusdual.lituraliabackspring.api.mapper.BookMapper;
import com.campusdual.lituraliabackspring.api.model.BookDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import com.campusdual.lituraliabackspring.repositories.BookRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    BookMapper mapper = BookMapper.INSTANCE;

    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(mapper, bookRepository);
    }

    @Test
    void getAllBooks() {
        //given
        Book book1 = Book.builder()
                         .bookId(1L)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        Book book2 = Book.builder()
                         .bookId(2L)
                         .isbn("123457")
                         .title("MacBeth")
                         .build();

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        //when
        List<BookDTO> bookDTOS = bookService.getAllBooks();

        //then
        assertEquals(2, bookDTOS.size());
    }

    @Test
    void getBookById() {
        //given
        Book book1 = Book.builder()
                         .bookId(1L)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(book1));

        //when
        BookDTO bookDTO = bookService.getBookById(1L);

        //then
        assertEquals("Hamlet", bookDTO.getTitle());

    }

    @Test()
    void getBookByIdNotFound() {
        Optional<Book> bookOptional = Optional.empty();

        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        Assertions.assertThrows(Exception.class, () -> {
            BookDTO book = bookService.getBookById(1L);
        });
    }

    @Test
    void createBook() {
        //given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Hamlet");

        Book savedBook = new Book();
        savedBook.setTitle(bookDTO.getTitle());
        savedBook.setBookId(1L);

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
        savedBook.setBookId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //when
        BookDTO savedDto = bookService.updateBook(1L, bookDTO);
        BookDTO savedDto1 = bookService.updateBook(bookDTO);

        //then
        assertEquals(bookDTO.getTitle(), savedDto.getTitle());
        assertEquals(bookDTO.getTitle(), savedDto1.getTitle());
    }

    @Test
    void deleteBookById() {
        Long id = 1L;
        bookService.deleteBookById(id);
        verify(bookRepository, times(1)).deleteById(anyLong());
    }
}