package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.mapper.dtos.BookDTO;
import com.campusdual.lituraliaopen.api.mapper.dtos.PublisherDTO;
import com.campusdual.lituraliaopen.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    PublisherService publisherService;


    @GetMapping
    public Page<PublisherDTO> getAllPublishers(@PageableDefault(page = 0, size = 10)
                                               @SortDefault.SortDefaults({@SortDefault(sort = "publisherId", direction = Direction.ASC)})
                                                   Pageable pageable) {
        return publisherService.getAllPublishers(pageable);
    }

    @GetMapping("/search")
    public Page<PublisherDTO> searchPublishers(@PageableDefault(page = 0, size = 10)
                                               @SortDefault.SortDefaults({@SortDefault(sort = "publisherId", direction = Direction.ASC)})
                                                   Pageable pageable,
                                               @RequestParam(required = false, defaultValue = "") String searchTerm) {
        return searchTerm.isEmpty() ?
               publisherService.getAllPublishers(pageable) :
               publisherService.searchPublishers(searchTerm, pageable);
    }

    @GetMapping("/{id}")
    public PublisherDTO getEmployeeById(@PathVariable("id") Integer id)
        throws ResourceNotFoundException {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherDTO createPublisher(@RequestBody PublisherDTO publisherDto) {
        return publisherService.createPublisher(publisherDto);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public PublisherDTO updatePublisher(@PathVariable Integer id, @RequestBody PublisherDTO customerDTO) {
        return publisherService.updatePublisher(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deletePublisher(@PathVariable Integer id) {
        publisherService.deletePublisherById(id);
    }

    // -------- Publisher's Books


    @GetMapping({"/{id}/books"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> getPublisherBooks(@PathVariable Integer id) {
        return publisherService.getPublisherBooks(id);
    }

    @PostMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> postPublisherBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return publisherService.setPublisherBook(id, idBook);
    }

    @PutMapping({"/{id}/books/{idBook}"})
    @ResponseStatus(HttpStatus.OK)
    public Slice<BookDTO> putPublisherBooks(@PathVariable Integer id, @PathVariable Integer idBook) {
        return publisherService.setPublisherBook(id, idBook);
    }

}
