package com.campusdual.lituraliaopen.api.mapper;

import com.campusdual.lituraliaopen.api.mapper.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.domain.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDTOToAuthor(AuthorDTO authorDto);

}
