package com.campusdual.lituraliaopen.api.mapper;

import com.campusdual.lituraliaopen.api.model.dtos.AuthorDTO;
import com.campusdual.lituraliaopen.domain.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDTOToAuthor(AuthorDTO authorDto);

    @Mappings({@Mapping(source = "id", target = "authorId")})
    Author idToAuthor(Integer id);

    default Integer authorToId(Author author) {
        return author.getAuthorId();
    }
}
