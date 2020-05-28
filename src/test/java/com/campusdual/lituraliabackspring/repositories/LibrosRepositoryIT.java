//package com.campusdual.lituraliabackspring.repositories;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.campusdual.lituraliabackspring.domain.Libro;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class LibrosRepositoryIT {
//
//    @Autowired
//    LibrosRepository repository;
//
//    @BeforeAll
//    static void beforeAll() {
//
//    }
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @Test
//    void findByTitulo() {
//        Optional<Libro> libroOptional = repository.findByTitulo("Lilting");
//        assertEquals("Lilting", libroOptional.get().getTitulo());
//    }
//
//    @Test
//    void findByTituloFail() {
//        Optional<Libro> libroOptional = repository.findByTitulo("ADSAADSDASD");
//        assert (!libroOptional.isPresent());
//    }
//}