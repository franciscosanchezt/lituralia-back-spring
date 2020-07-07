package com.campusdual.lituraliaopen.api.mapper.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthorDTO {

    private Integer authorId;
    private String authorName;
    private LocalDate authorBirth;
    private LocalDate authorDeath;

}
