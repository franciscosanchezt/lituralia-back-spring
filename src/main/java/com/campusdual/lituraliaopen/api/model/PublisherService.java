package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import java.util.List;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface PublisherService {

    List<PublisherDTO> getAllPublishers();

    PublisherDTO getPublisherById(Integer publisher_id) throws ResourceNotFoundException;

    PublisherDTO createPublisher(PublisherDTO publisherDto);

    PublisherDTO updatePublisher(PublisherDTO publisherDto);

    PublisherDTO updatePublisher(Integer publisher_id, PublisherDTO publisherDto);

    void deletePublisherById(Integer publisher_id) throws ResourceNotFoundException;

    List<PublisherDTO> getPublishersBySearchTerm(String searchTerm) throws ResourceNotFoundException;
}
