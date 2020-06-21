package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.AuthorMapper;
import com.campusdual.lituraliaopen.api.model.AuthorService;
import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.domain.Author;
import com.campusdual.lituraliaopen.repositories.AuthorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorMapper authorMapper;

    AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper     = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                               .stream()
                               .map(authorMapper::authorToAuthorDTO)
                               .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDTO> getAuthorsBySearchTerm(String searchTerm) throws ResourceNotFoundException {
        return authorRepository.findBySearchTerm(searchTerm)
                               .stream()
                               .map(authorMapper::authorToAuthorDTO)
                               .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(Integer author_id) {
        return authorRepository.findById(author_id)
                               .map(authorMapper::authorToAuthorDTO)
                               .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDto) {
        Author entity = authorRepository.save(authorMapper.authorDTOToAuthor(authorDto));
        return authorMapper.authorToAuthorDTO(entity);
    }

    @Override
    public AuthorDTO updateAuthor(Integer author_id, AuthorDTO authorDto) {
        Author author = authorMapper.authorDTOToAuthor(authorDto);
        author.setAuthorId(author_id);
        Author entity = authorRepository.save(author);
        return authorMapper.authorToAuthorDTO(entity);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDto) {
        return updateAuthor(authorDto.getAuthorId(), authorDto);
    }

    @Override
    public void deleteAuthorById(Integer author_id) throws ResourceNotFoundException {
        authorRepository.deleteById(author_id);
    }
}
