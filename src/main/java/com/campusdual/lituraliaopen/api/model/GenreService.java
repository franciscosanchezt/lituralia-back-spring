package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface GenreService {

    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(Integer genre_id) throws ResourceNotFoundException;

    GenreDTO createGenre(GenreDTO genreDto);

    GenreDTO updateGenre(GenreDTO genreDto);

    GenreDTO updateGenre(Integer genre_id, GenreDTO genreDto);

    void deleteGenreById(Integer genre_id) throws ResourceNotFoundException;

    List<GenreDTO> getGenresBySearchTerm(String searchTerm) throws ResourceNotFoundException;
}
