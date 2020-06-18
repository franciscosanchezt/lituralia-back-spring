package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.model.BookService;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    BookMapper bookMapper;

    BookRepository bookRepository;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper     = bookMapper;
        this.bookRepository = bookRepository;
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
    public BookDTO getBookById(Integer book_id) {
        return bookRepository.findById(book_id)
                             .map(bookMapper::bookToBookDTO)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO createBook(BookDTO bookDto) {
        Book entity = bookRepository.save(bookMapper.bookDTOToBook(bookDto));
        return bookMapper.bookToBookDTO(entity);
    }

    @Override
    public BookDTO updateBook(Integer book_id, BookDTO bookDto) {
        Book book = bookMapper.bookDTOToBook(bookDto);
        book.setBookId(book_id);
        Book entity = bookRepository.save(book);
        return bookMapper.bookToBookDTO(entity);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDto) {
        return updateBook(bookDto.getBookId(), bookDto);
    }

    @Override
    public void deleteBookById(Integer book_id) throws ResourceNotFoundException {
        bookRepository.deleteById(book_id);
    }
}
