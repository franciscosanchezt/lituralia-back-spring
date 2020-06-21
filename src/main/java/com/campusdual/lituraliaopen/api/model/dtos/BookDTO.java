package com.campusdual.lituraliaopen.api.model.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private List<Integer> genres = new ArrayList<>();
    private List<Integer> authors = new ArrayList<>();
}
