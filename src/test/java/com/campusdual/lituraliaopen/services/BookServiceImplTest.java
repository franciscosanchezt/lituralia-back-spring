package com.campusdual.lituraliaopen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.model.BookDTO;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.repositories.BookRepository;
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
                         .bookId(1)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        Book book2 = Book.builder()
                         .bookId(2)
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
}