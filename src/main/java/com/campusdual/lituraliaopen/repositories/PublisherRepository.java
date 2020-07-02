package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Publisher;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Optional<Publisher> findByPublisherName(String publisherName);


    Page<Publisher> findByPublisherNameContainingIgnoreCase(String searchTerm, Pageable pageable);
}
