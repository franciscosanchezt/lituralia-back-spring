//package com.campusdual.lituraliaopen.repositories;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import com.campusdual.lituraliaopen.domain.Book;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class BookRepositoryIT {
//
//    @Autowired
//    BookRepository repository;
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
//    @Disabled
//    void findByTitle() {
//        Optional<Book> bookOptional = repository.findByTitle("Die Hard");
//        assertEquals("Die Hard", bookOptional.get().getTitle());
//    }
//
//    @Test
//    @Disabled
//    void findByTitleFail() {
//        Optional<Book> bookOptional = repository.findByTitle("ADSAADSDASD");
//        assert (!bookOptional.isPresent());
//    }
//}