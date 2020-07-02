package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface PublisherService {

    Page<PublisherDTO> getAllPublishers(Pageable pageable);

    PublisherDTO getPublisherById(Integer publisher_id) throws ResourceNotFoundException;

    PublisherDTO createPublisher(PublisherDTO publisherDto);

    PublisherDTO updatePublisher(PublisherDTO publisherDto);

    PublisherDTO updatePublisher(Integer publisher_id, PublisherDTO publisherDto);

    void deletePublisherById(Integer publisher_id) throws ResourceNotFoundException;

    Page<PublisherDTO> searchPublishers(String searchTerm, Pageable pageable) throws ResourceNotFoundException;

    // -------- Publisher's Books

    Slice<BookDTO> getPublisherBooks(Integer publisherId) throws ResourceNotFoundException;

    Slice<BookDTO> setPublisherBook(Integer publisherId, Integer bookId) throws ResourceNotFoundException;

}
