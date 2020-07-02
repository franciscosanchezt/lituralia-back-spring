package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.mapper.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.mapper.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.mapper.dtos.GenreDTO;
import com.campusdual.lituraliaopen.api.mapper.dtos.PublisherDTO;
import com.campusdual.lituraliaopen.api.service.BookService;
import com.campusdual.lituraliaopen.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    PublisherService publisherService;

    @GetMapping
    public Page<BookDTO> getAllBooks(@PageableDefault(page = 0, size = 10)
                                     @SortDefault.SortDefaults({@SortDefault(sort = "bookId", direction = Direction.ASC)})
                                         Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/search")
    public Page<BookDTO> searchBooks(@PageableDefault(page = 0, size = 10)
                                     @SortDefault.SortDefaults({@SortDefault(sort = "bookId", direction = Direction.ASC)})
                                         Pageable pageable,
                                     @RequestParam(required = false, defaultValue = "") String searchTerm) {
        return searchTerm.isEmpty() ?
               bookService.getAllBooks(pageable) :
               bookService.searchBooks(searchTerm, pageable);
    }


    @GetMapping("/{id}")
    public BookDTO getEmployeeById(@PathVariable("id") Integer id)
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
    public BookDTO updateBook(@PathVariable Integer id, @RequestBody BookDTO customerDTO) {
        return bookService.updateBook(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
    }

    // -------- Book's Publisher


    @GetMapping({"/{id}/publisher"})
    @ResponseStatus(HttpStatus.OK)
    public PublisherDTO getBookPublisher(@PathVariable Integer id) {
        return bookService.getBookPublisher(id);
    }

    @PostMapping({"/{id}/publisher/{idPublisher}"})
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherDTO postBookPublisher(@PathVariable Integer id, @PathVariable Integer idPublisher) {
        return bookService.setBookPublisher(id, idPublisher);
    }

    @PutMapping({"/{id}/publisher/{idPublisher}"})
    @ResponseStatus(HttpStatus.OK)
    public PublisherDTO putBookPublisher(@PathVariable Integer id, @PathVariable Integer idPublisher) {
        return bookService.setBookPublisher(id, idPublisher);
    }

    // -------- Book's Genres


    @GetMapping({"/{id}/genres"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<GenreDTO> getBookGenres(@PathVariable Integer id) {
        return bookService.getBookGenres(id);
    }

    @PostMapping({"/{id}/genres/{idGenre}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<GenreDTO> postBookGenres(@PathVariable Integer id, @PathVariable Integer idGenre) {
        return bookService.setBookGenre(id, idGenre);
    }

    @PutMapping({"/{id}/genres/{idGenre}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<GenreDTO> putBookGenres(@PathVariable Integer id, @PathVariable Integer idGenre) {
        return bookService.setBookGenre(id, idGenre);
    }

    @DeleteMapping({"/{id}/genres/{idGenre}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<GenreDTO> deleteBookGenres(@PathVariable Integer id, @PathVariable Integer idGenre) {
        return bookService.deleteBookGenre(id, idGenre);
    }

    // -------- Book's Authors

    @GetMapping({"/{id}/authors"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<AuthorDTO> getBookAuthors(@PathVariable Integer id) {
        return bookService.getBookAuthors(id);
    }

    @PostMapping({"/{id}/authors/{idAuthor}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<AuthorDTO> postBookAuthors(@PathVariable Integer id, @PathVariable Integer idAuthor) {
        return bookService.setBookAuthor(id, idAuthor);
    }

    @PutMapping({"/{id}/authors/{idAuthor}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<AuthorDTO> putBookAuthors(@PathVariable Integer id, @PathVariable Integer idAuthor) {
        return bookService.setBookAuthor(id, idAuthor);
    }

    @DeleteMapping({"/{id}/authors/{idAuthor}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<AuthorDTO> deleteBookAuthors(@PathVariable Integer id, @PathVariable Integer idAuthor) {
        return bookService.deleteBookAuthor(id, idAuthor);
    }


}
