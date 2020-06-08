package com.campusdual.lituraliabackspring.api.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookDTO {

    private Long bookId;
    private String isbn;
    private String title;
    private String synopsis;
    private LocalDateTime publishDate;
    private byte[] cover;
    private int publisherId;
}
