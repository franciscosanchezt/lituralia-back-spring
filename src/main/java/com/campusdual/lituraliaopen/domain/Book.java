package com.campusdual.lituraliaopen.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String isbn;

    private String title;

    private String synopsis;

    @Column(name = "publish_date", columnDefinition = "DATE")
    private LocalDate publishDate;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] cover;

    @Column(name = "publisher_id")
    private int publisherId;
}
