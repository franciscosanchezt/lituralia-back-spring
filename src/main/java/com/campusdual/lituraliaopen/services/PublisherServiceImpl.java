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
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
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
    public Page<PublisherDTO> getAllPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable)
                                  .map(publisherMapper::publisherToPublisherDTO);
    }

    @Override
    public Page<PublisherDTO> searchPublishers(String searchTerm, Pageable pageable) throws ResourceNotFoundException {
        return publisherRepository.findByPublisherNameContainingIgnoreCase(searchTerm, pageable)
                                  .map(publisherMapper::publisherToPublisherDTO);
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
    public Slice<BookDTO> getPublisherBooks(Integer publisherId) throws ResourceNotFoundException {
        return new SliceImpl<>(publisherRepository.findById(publisherId)
                                                  .map(publisher -> {
                                                      return publisher.getBooks().stream()
                                                                      .map(bookMapper::bookToBookDTO)
                                                                      .collect(Collectors.toList());
                                                  })
                                                  .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public Slice<BookDTO> setPublisherBook(Integer publisherId, Integer bookId) throws ResourceNotFoundException {
        return new SliceImpl<>(publisherRepository.findById(publisherId)
                                                  .map(publisher -> {
                                                      Book book = bookRepository.findById(bookId)
                                                                                .orElseThrow(ResourceNotFoundException::new);
                                                      book.setPublisher(publisher);
                                                      bookRepository.saveAndFlush(book);//TODO: check if added book ins in returned slice
                                                      return publisher.getBooks().stream()
                                                                      .map(bookMapper::bookToBookDTO)
                                                                      .collect(Collectors.toList());
                                                  })
                                                  .orElseThrow(ResourceNotFoundException::new));
    }
}
