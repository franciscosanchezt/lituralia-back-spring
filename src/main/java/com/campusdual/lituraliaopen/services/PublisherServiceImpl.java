package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.BookMapper;
import com.campusdual.lituraliaopen.api.mapper.PublisherMapper;
import com.campusdual.lituraliaopen.api.model.PublisherService;
import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import com.campusdual.lituraliaopen.domain.Book;
import com.campusdual.lituraliaopen.domain.Publisher;
import com.campusdual.lituraliaopen.repositories.BookRepository;
import com.campusdual.lituraliaopen.repositories.PublisherRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public PublisherServiceImpl(PublisherMapper publisherMapper, PublisherRepository publisherRepository,
                                BookRepository bookRepository, BookMapper bookMapper) {
        this.publisherMapper     = publisherMapper;
        this.publisherRepository = publisherRepository;
        this.bookRepository      = bookRepository;
        this.bookMapper          = bookMapper;
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll()
                                  .stream()
                                  .map(publisherMapper::publisherToPublisherDTO)
                                  .collect(Collectors.toList());
    }

    @Override
    public List<PublisherDTO> getPublishersBySearchTerm(String searchTerm) throws ResourceNotFoundException {
        return publisherRepository.findBySearchTerm(searchTerm)
                                  .stream()
                                  .map(publisherMapper::publisherToPublisherDTO)
                                  .collect(Collectors.toList());
    }

    @Override
    public PublisherDTO getPublisherById(Integer publisher_id) {
        return publisherRepository.findById(publisher_id)
                                  .map(publisherMapper::publisherToPublisherDTO)
                                  .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDto) {
        Publisher entity = publisherRepository.save(publisherMapper.publisherDTOToPublisher(publisherDto));
        return publisherMapper.publisherToPublisherDTO(entity);
    }

    @Override
    public PublisherDTO updatePublisher(Integer publisher_id, PublisherDTO publisherDto) {
        Publisher publisher = publisherMapper.publisherDTOToPublisher(publisherDto);
        publisher.setPublisherId(publisher_id);
        Publisher entity = publisherRepository.save(publisher);
        return publisherMapper.publisherToPublisherDTO(entity);
    }

    @Override
    public PublisherDTO updatePublisher(PublisherDTO publisherDto) {
        return updatePublisher(publisherDto.getPublisherId(), publisherDto);
    }

    @Override
    public void deletePublisherById(Integer publisher_id) throws ResourceNotFoundException {
        publisherRepository.deleteById(publisher_id);
    }

    // -------- Publisher's Books

    @Override
    public Set<BookDTO> getPublisherBooks(Integer publisherId) throws ResourceNotFoundException {
        return publisherRepository.findById(publisherId)
                                  .map(publisher -> {
                                      return publisher.getBooks().stream()
                                                      .map(bookMapper::bookToBookDTO)
                                                      .collect(Collectors.toSet());
                                  })
                                  .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public BookDTO setPublisherBook(Integer publisherId, Integer bookId) throws ResourceNotFoundException {
        return publisherRepository.findById(publisherId)
                                  .map(publisher -> {
                                      Book book = bookRepository.findById(bookId)
                                                                .orElseThrow(ResourceNotFoundException::new);
                                      book.setPublisher(publisher);
                                      return bookMapper.bookToBookDTO(bookRepository.saveAndFlush(book));
                                  })
                                  .orElseThrow(ResourceNotFoundException::new);
    }
}
