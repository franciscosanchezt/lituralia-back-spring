package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.model.GenreService;
import com.campusdual.lituraliaopen.api.model.Paging;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
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
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping
    public ListDTO<GenreDTO> getAllGenres(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(required = false, defaultValue = "") String searchTerm) {
        ListDTO<GenreDTO> genres = new ListDTO<>();

        List<GenreDTO> allGenres;
        if (searchTerm.isEmpty() || searchTerm.equals("null")) {
            allGenres = genreService.getAllGenres();
        } else {
            allGenres = genreService.getGenresBySearchTerm(searchTerm);
        }
        if (pageNumber < 1) {
            genres.getData().addAll(allGenres);
            genres.setPaging(Paging.builder()
                                   .pageNumber(1)
                                   .numberOfPages(1)
                                   .pageSize(genres.getData().size())
                                   .build());
        } else {
            int maxPage = (allGenres.size() / pageSize) + (allGenres.size() % pageSize == 0 ? 0 : 1);
            pageSize   = Math.min(pageSize, 50);
            pageSize   = Math.max(pageSize, 10);
            pageNumber = Math.min(pageNumber, maxPage);
            pageNumber = Math.max(pageNumber, 1);
            genres.getData().addAll(allGenres.stream()
                                             .skip(Math.max(0, pageSize * (pageNumber - 1)))
                                             .limit(pageSize)
                                             .collect(Collectors.toList()));
            genres.setPaging(Paging.builder()
                                   .pageNumber(pageNumber)
                                   .numberOfPages(maxPage)
                                   .pageSize(pageSize)
                                   .searchTerm(searchTerm)
                                   .build());
        }
        return genres;
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
}
