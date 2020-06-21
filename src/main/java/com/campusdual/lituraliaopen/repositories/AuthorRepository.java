package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByAuthorName(String authorName);

    @Query("SELECT p FROM authors p WHERE LOWER(p.authorName) like LOWER(CONCAT('%',:searchTerm, '%') )")
    List<Author> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
