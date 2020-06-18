package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.Paging;
import com.campusdual.lituraliaopen.api.model.BookService;
import com.campusdual.lituraliaopen.api.model.PublisherService;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import com.campusdual.lituraliaopen.api.model.dtos.ListDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    public ListDTO<BookDTO> getAllBooks(@RequestParam(required = false, defaultValue = GlobalController.PAGINATION_DEFAULT_PAGE_NUM) Integer pageNumber,
                                        @RequestParam(required = false, defaultValue = GlobalController.PAGINATION_DEFAULT_PAGE_SIZE) Integer pageSize,
                                        @RequestParam(required = false, defaultValue = "") String searchTerm) {
        ListDTO<BookDTO> books = new ListDTO<>();

        List<BookDTO> allBooks;
        if (searchTerm.isEmpty() || searchTerm.equals("null")) {
            allBooks = bookService.getAllBooks();
        } else {
            allBooks = bookService.getBooksBySearchTerm(searchTerm);
        }
        if (pageNumber < 1) {
            books.setData(allBooks);
            books.setPaging(Paging.builder()
                                  .pageNumber(1)
                                  .numberOfPages(1)
                                  .pageSize(allBooks.size())
                                  .build());
        } else {
            int maxPage = (allBooks.size() / pageSize) + (allBooks.size() % pageSize == 0 ? 0 : 1);
            pageSize   = Math.min(pageSize, 50);
            pageSize   = Math.max(pageSize, 10);
            pageNumber = Math.min(pageNumber, maxPage);
            pageNumber = Math.max(pageNumber, 1);
            books.setData(allBooks.stream()
                                  .skip(Math.max(0, pageSize * (pageNumber - 1)))
                                  .limit(pageSize)
                                  .collect(Collectors.toList()));
            books.setPaging(Paging.builder()
                                  .pageNumber(pageNumber)
                                  .numberOfPages(maxPage)
                                  .pageSize(pageSize)
                                  .searchTerm(searchTerm)
                                  .build());
        }
        return books;
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


    @GetMapping({"/{id}/publisher/"})
    @ResponseStatus(HttpStatus.OK)
    public PublisherDTO getBookPublisher(@PathVariable Integer id) {
        return bookService.getBookPublisher(id);
    }

    @PostMapping({"/{id}/publisher/{idPublisher}"})
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO postBookPublisher(@PathVariable Integer id, @PathVariable Integer idPublisher) {
        return bookService.setBookPublisher(id, idPublisher);
    }

    @PutMapping({"/{id}/publisher/{idPublisher}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO putBookPublisher(@PathVariable Integer id, @PathVariable Integer idPublisher) {
        return bookService.setBookPublisher(id, idPublisher);
    }

    @GetMapping({"/{id}/genres/"})
    @ResponseStatus(HttpStatus.OK)
    public ListDTO<GenreDTO> getBookGenres(@PathVariable Integer id) {
        return new ListDTO<>(bookService.getBookGenres(id));
    }

    @PostMapping({"/{id}/genres/{idGenre}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO postBookGenres(@PathVariable Integer id, @PathVariable Integer idGenre) {
        return bookService.setBookGenre(id, idGenre);
    }

    @PutMapping({"/{id}/genres/{idGenre}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO putBookGenres(@PathVariable Integer id, @PathVariable Integer idGenre) {
        return bookService.setBookGenre(id, idGenre);
    }

    @DeleteMapping({"/{id}/genres/{idGenre}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO deleteBookGenres(@PathVariable Integer id, @PathVariable Integer idGenre) {
        return bookService.deleteBookGenre(id, idGenre);
    }


}
