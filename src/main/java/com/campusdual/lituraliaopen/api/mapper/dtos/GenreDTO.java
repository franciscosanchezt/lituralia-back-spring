package com.campusdual.lituraliaopen.api.mapper.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenreDTO {

    private Integer genreId;
    private String genreName;
    private String genreDesc;

}
