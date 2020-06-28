package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.model.AuthorService;
import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
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
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @GetMapping
    public Page<AuthorDTO> getAllAuthors(@PageableDefault(page = 0, size = 10)
                                         @SortDefault.SortDefaults({@SortDefault(sort = "authorId", direction = Direction.ASC)})
                                             Pageable pageable) {
        return authorService.getAllAuthors(pageable);
    }

    @GetMapping("/search")
    public Page<AuthorDTO> searchAuthors(@PageableDefault(page = 0, size = 10)
                                         @SortDefault.SortDefaults({@SortDefault(sort = "authorId", direction = Direction.ASC)})
                                             Pageable pageable,
                                         @RequestParam(required = false, defaultValue = "") String searchTerm) {
        return searchTerm.isEmpty() ?
               authorService.getAllAuthors(pageable) :
               authorService.searchAuthors(searchTerm, pageable);
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
    public Slice<BookDTO> getAuthorBooks(@PathVariable Integer id) {
        return authorService.getAuthorBooks(id);
    }

    @PostMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> postAuthorBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return authorService.setAuthorBook(id, idBook);
    }

    @PutMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> putAuthorBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return authorService.setAuthorBook(id, idBook);
    }

    @DeleteMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> deleteAuthorBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return authorService.deleteAuthorBook(id, idBook);
    }

}
