package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.api.mapper.LibroMapper;
import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import com.campusdual.lituraliabackspring.repositories.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    LibroMapper bookMapper;

    BookRepository bookRepository;

    public BookServiceImpl(LibroMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper     = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<LibroDTO> getAllBooks() {
        return bookRepository.findAll()
                             .stream()
                             .map(bookMapper::libroToLibroDTO)
                             .collect(Collectors.toList());
    }

    @Override
    public LibroDTO getBookById(Long book_id) {
        return bookRepository.findById(book_id)
                             .map(bookMapper::libroToLibroDTO)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public LibroDTO createBook(LibroDTO libroDto) {
        Book entity = bookRepository.save(bookMapper.libroDTOToLibro(libroDto));
        return bookMapper.libroToLibroDTO(entity);
    }

    @Override
    public LibroDTO updateBook(Long book_id, LibroDTO libroDto) {
        Book book = bookMapper.libroDTOToLibro(libroDto);
        book.setBookId(book_id);
        Book entity = bookRepository.save(book);
        return bookMapper.libroToLibroDTO(entity);
    }

    @Override
    public LibroDTO updateBook(LibroDTO libroDto) {
        return updateBook(libroDto.getIdLibro(), libroDto);
    }


    @Override
    public void deleteBook(LibroDTO libro) throws ResourceNotFoundException {
        deleteBookById(libro.getIdLibro());
    }

    @Override
    public void deleteBookById(Long book_id) throws ResourceNotFoundException {
        bookRepository.deleteById(book_id);
    }
}
