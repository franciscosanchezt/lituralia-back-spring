package com.campusdual.lituraliabackspring.controllers;

import com.campusdual.lituraliabackspring.api.model.BookDTO;
import com.campusdual.lituraliabackspring.api.model.BookListDTO;
import com.campusdual.lituraliabackspring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public BookListDTO getAllBooks() {
        BookListDTO Books = new BookListDTO();
        Books.getBooks().addAll(bookService.getAllBooks());
        return Books;
    }

    @GetMapping("/{id}")
    public BookDTO getEmployeeById(@PathVariable("id") Long id)
        throws ResourceNotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody BookDTO bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO customerDTO) {
        return bookService.updateBook(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
