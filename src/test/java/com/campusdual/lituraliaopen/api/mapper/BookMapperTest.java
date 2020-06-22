package com.campusdual.lituraliaopen.api.mapper;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookMapperImpl.class, PublisherMapperImpl.class, GenreMapperImpl.class, AuthorMapperImpl.class})
class BookMapperTest {

    @Autowired
    BookMapper mapper;

    //    public static final String TITLE = "Hamlet";
//    public static final String ISBN = "123456";
    public static final int ID = 1;

//    @Test
//    void bookToBookDTO() {
//        //given
//        Publisher publisher = Publisher.builder().publisherId(1).build();
//        Genre genre = Genre.builder().genreId(1).build();
//        Author author = Author.builder().authorId(1).build();
//        Book book1 = Book.builder()
//                         .bookId(ID)
//                         .isbn(ISBN)
//                         .title(TITLE)
//                         .publisher(publisher)
//                         .genres(Collections.singletonList(genre))
//                         .authors(Collections.singletonList(author))
//                         .build();
//
//        //when
//        BookDTO bookDTO = mapper.bookToBookDTO(book1);
//
//        //then
//        assertEquals(Integer.valueOf(ID), bookDTO.getBookId());
//        assertEquals(ISBN, bookDTO.getIsbn());
//        assertEquals(TITLE, bookDTO.getTitle());
//    }
//
//    @Test
//    void bookDTOToBook() {
//        //given
//        BookDTO bookDTO = BookDTO.builder()
//                                 .bookId(ID)
//                                 .isbn(ISBN)
//                                 .title(TITLE)
//                                 .build();
//
//        //when
//        Book book = mapper.bookDTOToBook(bookDTO);
//
//        //then
//        assertEquals(Integer.valueOf(ID), book.getBookId());
//        assertEquals(ISBN, book.getIsbn());
//        assertEquals(TITLE, book.getTitle());
//    }
//
//    @Test
//    void idToBook() {
//        //given
//        Integer id = ID;
//
//        //whendejame p
//        Book book = mapper.idToBook(id);
//
//        //then
//        assertEquals(ID, book.getBookId());
//    }
//
//    @Test
//    void bookToId() {
//        //given
//        Book book = Book.builder()
//                        .bookId(ID)
//                        .build();
//
//        //when
//        Integer id = mapper.bookToId(book);
//
//        //then
//        assertEquals(ID, id);
//    }
}