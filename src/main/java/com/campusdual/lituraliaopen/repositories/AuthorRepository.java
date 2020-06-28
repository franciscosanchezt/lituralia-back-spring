package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Author;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByAuthorName(String authorName);

    Page<Author> findByAuthorNameContainingIgnoreCase(String searchTerm, Pageable pageable);
}
