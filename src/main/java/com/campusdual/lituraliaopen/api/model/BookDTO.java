package com.campusdual.lituraliaopen.api.model;

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
    private byte[] cover;
    private int publisherId;
}
