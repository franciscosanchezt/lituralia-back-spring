package com.campusdual.lituraliabackspring.api.mapper;

import com.campusdual.lituraliabackspring.api.model.LibroDTO;
import com.campusdual.lituraliabackspring.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LibroMapper {

    LibroMapper INSTANCE = Mappers.getMapper(LibroMapper.class);

    LibroDTO libroToLibroDTO(Book book);

    Book libroDTOToLibro(LibroDTO libroDto);
}
