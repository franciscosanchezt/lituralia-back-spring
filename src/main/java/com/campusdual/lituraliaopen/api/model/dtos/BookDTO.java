package com.campusdual.lituraliaopen.api.model.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookDTO {

    private Integer bookId;
    private String isbn;
    private String title;
    private String synopsis;
    private LocalDate publishDate;
    private String cover;
    private Integer publisher;
}
