package com.campusdual.lituraliaopen.repositories;

import com.campusdual.lituraliaopen.domain.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

    List<Book> findByTitleContainingOrderByTitle(String likeTitle);

    @Query("SELECT b FROM books b WHERE LOWER(b.title) like LOWER(CONCAT('%',:searchTerm, '%') )")
    List<Book> findBySearchTerm(@Param("searchTerm") String searchTerm);

}
