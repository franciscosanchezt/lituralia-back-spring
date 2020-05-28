package com.campusdual.lituraliabackspring.repositories;

import com.campusdual.lituraliabackspring.domain.Libro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

}
