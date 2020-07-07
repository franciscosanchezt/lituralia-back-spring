package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.mapper.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.mapper.dtos.GenreDTO;
import com.campusdual.lituraliaopen.api.service.GenreService;
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
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    GenreService genreService;


    @GetMapping
    public Page<GenreDTO> getAllGenres(@PageableDefault(page = 0, size = 10)
                                       @SortDefault.SortDefaults({@SortDefault(sort = "genreId", direction = Direction.ASC)})
                                           Pageable pageable) {
        return genreService.getAllGenres(pageable);
    }

    @GetMapping("/search")
    public Page<GenreDTO> searchGenres(@PageableDefault(page = 0, size = 10)
                                       @SortDefault.SortDefaults({@SortDefault(sort = "genreId", direction = Direction.ASC)})
                                           Pageable pageable,
                                       @RequestParam(required = false, defaultValue = "") String searchTerm) {
        return searchTerm.isEmpty() ?
               genreService.getAllGenres(pageable) :
               genreService.searchGenres(searchTerm, pageable);
    }


    @GetMapping("/{id}")
    public GenreDTO getEmployeeById(@PathVariable("id") Integer id)
        throws ResourceNotFoundException {
        return genreService.getGenreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDTO createGenre(@RequestBody GenreDTO genreDto) {
        return genreService.createGenre(genreDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO updateGenre(@PathVariable Integer id, @RequestBody GenreDTO customerDTO) {
        return genreService.updateGenre(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteGenre(@PathVariable Integer id) {
        genreService.deleteGenreById(id);
    }

    // -------- Genre's Books


    @GetMapping({"/{id}/books"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> getGenreBooks(@PathVariable Integer id) {
        return genreService.getGenreBooks(id);
    }

    @PostMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> postGenreBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return genreService.setGenreBook(id, idBook);
    }

    @PutMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> putGenreBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return genreService.setGenreBook(id, idBook);
    }

    @DeleteMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> deleteGenreBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return genreService.deleteGenreBook(id, idBook);
    }
}
