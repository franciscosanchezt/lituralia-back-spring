package com.campusdual.lituraliaopen.api.mapper;

import com.campusdual.lituraliaopen.api.model.dtos.BookDTO;
import com.campusdual.lituraliaopen.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PublisherMapper.class, GenreMapper.class, AuthorMapper.class})
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDto);

    @Mappings({@Mapping(source = "id", target = "bookId")})
    Book idToBook(Integer id);

    default Integer bookToId(Book book) {
        return book.getBookId();
    }

}
