package com.campusdual.lituraliaopen.api.mapper;

import com.campusdual.lituraliaopen.api.mapper.dtos.GenreDTO;
import com.campusdual.lituraliaopen.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO genreToGenreDTO(Genre genre);

    Genre genreDTOToGenre(GenreDTO genreDto);

}
