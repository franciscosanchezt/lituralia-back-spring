package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.GenreMapper;
import com.campusdual.lituraliaopen.api.model.GenreService;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import com.campusdual.lituraliaopen.domain.Genre;
import com.campusdual.lituraliaopen.repositories.GenreRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    GenreMapper genreMapper;

    GenreRepository genreRepository;

    public GenreServiceImpl(GenreMapper genreMapper, GenreRepository genreRepository) {
        this.genreMapper     = genreMapper;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                              .stream()
                              .map(genreMapper::genreToGenreDTO)
                              .collect(Collectors.toList());
    }

    @Override
    public List<GenreDTO> getGenresBySearchTerm(String searchTerm) throws ResourceNotFoundException {
        return genreRepository.findBySearchTerm(searchTerm)
                              .stream()
                              .map(genreMapper::genreToGenreDTO)
                              .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(Integer genre_id) {
        return genreRepository.findById(genre_id)
                              .map(genreMapper::genreToGenreDTO)
                              .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDto) {
        Genre entity = genreRepository.save(genreMapper.genreDTOToGenre(genreDto));
        return genreMapper.genreToGenreDTO(entity);
    }

    @Override
    public GenreDTO updateGenre(Integer genre_id, GenreDTO genreDto) {
        Genre genre = genreMapper.genreDTOToGenre(genreDto);
        genre.setGenreId(genre_id);
        Genre entity = genreRepository.save(genre);
        return genreMapper.genreToGenreDTO(entity);
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDto) {
        return updateGenre(genreDto.getGenreId(), genreDto);
    }

    @Override
    public void deleteGenreById(Integer genre_id) throws ResourceNotFoundException {
        genreRepository.deleteById(genre_id);
    }
}
