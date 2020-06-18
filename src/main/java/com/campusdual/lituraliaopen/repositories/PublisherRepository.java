package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Publisher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Optional<Publisher> findByPublisherName(String publisherName);

    @Query("SELECT p FROM publishers p WHERE LOWER(p.publisherName) like LOWER(CONCAT('%',:searchTerm, '%') )")
    List<Publisher> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
