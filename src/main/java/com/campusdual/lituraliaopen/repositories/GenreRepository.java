package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Genre;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Optional<Genre> findByGenreName(String genreName);

    @Query("SELECT p FROM genres p WHERE LOWER(p.genreName) like LOWER(CONCAT('%',:searchTerm, '%') )")
    List<Genre> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
