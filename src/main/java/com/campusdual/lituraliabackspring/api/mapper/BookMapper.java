package com.campusdual.lituraliabackspring.api.mapper;

import com.campusdual.lituraliabackspring.api.model.BookDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDto);
}
