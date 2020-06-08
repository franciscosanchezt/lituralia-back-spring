package com.campusdual.lituraliabackspring.controllers;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.api.model.LibroListDTO;
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
    public LibroListDTO getAllLibros() {
        LibroListDTO libros = new LibroListDTO();
        libros.getLibros().addAll(bookService.getAllBooks());
        return libros;
    }

    @GetMapping("/{id}")
    public LibroDTO getEmployeeById(@PathVariable("id") Long id)
        throws ResourceNotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibroDTO createLibro(@RequestBody LibroDTO libroDto) {
        return bookService.createBook(libroDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public LibroDTO updateLibro(@PathVariable Long id, @RequestBody LibroDTO customerDTO) {
        return bookService.updateBook(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteLibro(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
