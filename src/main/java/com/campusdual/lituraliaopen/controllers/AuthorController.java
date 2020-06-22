package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.Paging;
import com.campusdual.lituraliaopen.api.model.AuthorService;
import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.ListDTO;
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
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ListDTO<AuthorDTO> getAllAuthors(@RequestParam(required = false, defaultValue = GlobalController.PAGINATION_DEFAULT_PAGE_NUM) Integer pageNumber,
                                            @RequestParam(required = false, defaultValue = GlobalController.PAGINATION_DEFAULT_PAGE_SIZE) Integer pageSize,
                                            @RequestParam(required = false, defaultValue = "") String searchTerm) {
        ListDTO<AuthorDTO> authors = new ListDTO<>();

        List<AuthorDTO> allAuthors;
        if (searchTerm.isEmpty() || searchTerm.equals("null")) {
            allAuthors = authorService.getAllAuthors();
        } else {
            allAuthors = authorService.getAuthorsBySearchTerm(searchTerm);
        }
        if (pageNumber < 1) {
            authors.setData(allAuthors);
            authors.setPaging(Paging.builder()
                                    .pageNumber(0)
                                    .numberOfPages(1)
                                    .pageSize(authors.getData().size())
                                    .build());
        } else {
            int maxPage = (allAuthors.size() / pageSize) + (allAuthors.size() % pageSize == 0 ? 0 : 1);
            pageSize   = Math.min(pageSize, 50);
            pageSize   = Math.max(pageSize, 10);
            pageNumber = Math.min(pageNumber, maxPage);
            pageNumber = Math.max(pageNumber, 1);
            authors.setData(allAuthors.stream()
                                      .skip(Math.max(0, pageSize * (pageNumber - 1)))
                                      .limit(pageSize)
                                      .collect(Collectors.toList()));
            authors.setPaging(Paging.builder()
                                    .pageNumber(pageNumber)
                                    .numberOfPages(maxPage)
                                    .pageSize(pageSize)
                                    .searchTerm(searchTerm)
                                    .build());
        }
        return authors;
    }


    @GetMapping("/{id}")
    public AuthorDTO getEmployeeById(@PathVariable("id") Integer id)
        throws ResourceNotFoundException {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDto) {
        return authorService.createAuthor(authorDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTO customerDTO) {
        return authorService.updateAuthor(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);
    }

    // -------- Author's Books


    @GetMapping({"/{id}/books"})
    @ResponseStatus(HttpStatus.OK)
    public ListDTO<BookDTO> getAuthorBooks(@PathVariable Integer id) {
        return new ListDTO<>(authorService.getAuthorBooks(id));
    }

    @PostMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO postAuthorBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return authorService.setAuthorBook(id, idBook);
    }

    @PutMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO putAuthorBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return authorService.setAuthorBook(id, idBook);
    }

    @DeleteMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO deleteAuthorBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return authorService.deleteAuthorBook(id, idBook);
    }

}
