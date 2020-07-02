package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface GenreService {

    Page<GenreDTO> getAllGenres(Pageable pageable);

    GenreDTO getGenreById(Integer genre_id) throws ResourceNotFoundException;

    GenreDTO createGenre(GenreDTO genreDto);

    GenreDTO updateGenre(GenreDTO genreDto);

    GenreDTO updateGenre(Integer genre_id, GenreDTO genreDto);

    void deleteGenreById(Integer genre_id) throws ResourceNotFoundException;

    Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable) throws ResourceNotFoundException;

    // -------- Genre's Books

    Slice<BookDTO> getGenreBooks(Integer genreId) throws ResourceNotFoundException;

    Slice<BookDTO> setGenreBook(Integer genreId, Integer bookId) throws ResourceNotFoundException;

    Slice<BookDTO> deleteGenreBook(Integer genreId, Integer bookId) throws ResourceNotFoundException;
}
