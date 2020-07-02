package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Genre;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Optional<Genre> findByGenreName(String genreName);

    Page<Genre> findByGenreNameContainingIgnoreCase(String searchTerm, Pageable pageable);
}
