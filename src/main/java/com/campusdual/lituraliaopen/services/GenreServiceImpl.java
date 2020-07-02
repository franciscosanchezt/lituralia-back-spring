package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.mapper.GenreMapper;
import com.campusdual.lituraliaopen.api.model.GenreService;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.GenreDTO;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.domain.Genre;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import com.campusdual.lituraliaopen.repositories.GenreRepository;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {


    GenreRepository genreRepository;
    GenreMapper genreMapper;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper,
                            BookRepository bookRepository, BookMapper bookMapper) {
        this.genreMapper     = genreMapper;
        this.genreRepository = genreRepository;
        this.bookRepository  = bookRepository;
        this.bookMapper      = bookMapper;
    }


    @Override
    public Page<GenreDTO> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable)
                              .map(genreMapper::genreToGenreDTO);
    }

    @Override
    public Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable) throws ResourceNotFoundException {
        return genreRepository.findByGenreNameContainingIgnoreCase(searchTerm, pageable)
                              .map(genreMapper::genreToGenreDTO);
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

    // -------- Genre's Books

    @Override
    public Slice<BookDTO> getGenreBooks(Integer genreId) throws ResourceNotFoundException {
        return new SliceImpl<>(genreRepository.findById(genreId)
                                              .map(genre -> {
                                                  return genre.getBooks().stream()
                                                              .map(bookMapper::bookToBookDTO)
                                                              .collect(Collectors.toList());
                                              })
                                              .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Slice<BookDTO> setGenreBook(Integer genreId, Integer bookId) throws ResourceNotFoundException {
        return new SliceImpl<>(genreRepository.findById(genreId)
                                              .map(genre -> {
                                                  Book book = bookRepository.findById(bookId)
                                                                            .orElseThrow(ResourceNotFoundException::new);
                                                  book.getGenres().add(genre);
                                                  bookRepository.saveAndFlush(book);//TODO: check if added book ins in returned slice
                                                  return genre.getBooks().stream()
                                                              .map(bookMapper::bookToBookDTO)
                                                              .collect(Collectors.toList());
                                              })
                                              .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Slice<BookDTO> deleteGenreBook(Integer genreId, Integer bookId) throws ResourceNotFoundException {
        return new SliceImpl<>(genreRepository.findById(genreId)
                                              .map(genre -> {
                                                  Book book = bookRepository.findById(bookId)
                                                                            .orElseThrow(ResourceNotFoundException::new);
                                                  book.getGenres().remove(genre);
                                                  bookRepository.saveAndFlush(book);
                                                  return genre.getBooks().stream()
                                                              .map(bookMapper::bookToBookDTO)
                                                              .collect(Collectors.toList());
                                              })
                                              .orElseThrow(ResourceNotFoundException::new));
    }
}
