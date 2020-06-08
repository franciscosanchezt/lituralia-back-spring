package com.campusdual.lituraliabackspring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.campusdual.lituraliabackspring.api.mapper.LibroMapper;
import com.campusdual.lituraliabackspring.api.model.LibroDTO;
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

    LibroMapper mapper = LibroMapper.INSTANCE;

    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(mapper, bookRepository);
    }

    @Test
    void getAllLibros() {
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
        List<LibroDTO> libroDTOs = bookService.getAllBooks();

        //then
        assertEquals(2, libroDTOs.size());
    }

    @Test
    void getLibroById() {
        //given
        Book book1 = Book.builder()
                         .bookId(1L)
                         .isbn("123456")
                         .title("Hamlet")
                         .build();
        when(bookRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(book1));

        //when
        LibroDTO libroDTO = bookService.getBookById(1L);

        //then
        assertEquals("Hamlet", libroDTO.getTitulo());

    }

    @Test()
    void getLibroByIdNotFound() {
        Optional<Book> libroOptional = Optional.empty();

        when(bookRepository.findById(anyLong())).thenReturn(libroOptional);

        Assertions.assertThrows(Exception.class, () -> {
            LibroDTO libro = bookService.getBookById(1L);
        });
    }

    @Test
    void createLibro() {
        //given
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setTitulo("Hamlet");

        Book savedBook = new Book();
        savedBook.setTitle(libroDTO.getTitulo());
        savedBook.setBookId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //when
        LibroDTO savedDto = bookService.createBook(libroDTO);

        //then
        assertEquals(libroDTO.getTitulo(), savedDto.getTitulo());
    }

    @Test
    void updateLibro() {
        //given
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setTitulo("Hamlet");

        Book savedBook = new Book();
        savedBook.setTitle(libroDTO.getTitulo());
        savedBook.setBookId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //when
        LibroDTO savedDto = bookService.updateBook(1L, libroDTO);
        LibroDTO savedDto1 = bookService.updateBook(libroDTO);

        //then
        assertEquals(libroDTO.getTitulo(), savedDto.getTitulo());
        assertEquals(libroDTO.getTitulo(), savedDto1.getTitulo());
    }

    @Test
    void deleteLibroById() {
        Long id = 1L;
        bookService.deleteBookById(id);
        verify(bookRepository, times(1)).deleteById(anyLong());
    }
}