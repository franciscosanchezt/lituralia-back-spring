package com.campusdual.lituraliaopen.controllers;

import com.campusdual.lituraliaopen.api.model.Paging;
import com.campusdual.lituraliaopen.api.model.PublisherService;
import com.campusdual.lituraliaopen.api.model.dtos.ListDTO;
import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    public ListDTO<PublisherDTO> getAllPublishers(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  @RequestParam(required = false, defaultValue = "") String searchTerm) {
        ListDTO<PublisherDTO> publishers = new ListDTO<>();

        List<PublisherDTO> allPublishers;
        if (searchTerm.isEmpty() || searchTerm.equals("null")) {
            allPublishers = publisherService.getAllPublishers();
        } else {
            allPublishers = publisherService.getPublishersBySearchTerm(searchTerm);
        }
        if (pageNumber < 1) {
            publishers.getData().addAll(allPublishers);
            publishers.setPaging(Paging.builder()
                                       .pageNumber(1)
                                       .numberOfPages(1)
                                       .pageSize(publishers.getData().size())
                                       .build());
        } else {
            int maxPage = (allPublishers.size() / pageSize) + (allPublishers.size() % pageSize == 0 ? 0 : 1);
            pageSize   = Math.min(pageSize, 50);
            pageSize   = Math.max(pageSize, 10);
            pageNumber = Math.min(pageNumber, maxPage);
            pageNumber = Math.max(pageNumber, 1);
            publishers.getData().addAll(allPublishers.stream()
                                                     .skip(Math.max(0, pageSize * (pageNumber - 1)))
                                                     .limit(pageSize)
                                                     .collect(Collectors.toList()));
            publishers.setPaging(Paging.builder()
                                       .pageNumber(pageNumber)
                                       .numberOfPages(maxPage)
                                       .pageSize(pageSize)
                                       .searchTerm(searchTerm)
                                       .build());
        }
        return publishers;
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
}
