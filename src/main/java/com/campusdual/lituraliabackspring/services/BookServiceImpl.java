package com.campusdual.lituraliabackspring.services;

import com.campusdual.lituraliabackspring.api.mapper.BookMapper;
import com.campusdual.lituraliabackspring.api.model.BookDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import com.campusdual.lituraliabackspring.repositories.BookRepository;
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
    public BookDTO getBookById(Long book_id) {
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
    public BookDTO updateBook(Long book_id, BookDTO bookDto) {
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
    public void deleteBook(BookDTO book) throws ResourceNotFoundException {
        deleteBookById(book.getBookId());
    }

    @Override
    public void deleteBookById(Long book_id) throws ResourceNotFoundException {
        bookRepository.deleteById(book_id);
    }
}
