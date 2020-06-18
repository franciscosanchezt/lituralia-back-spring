package com.campusdual.lituraliaopen.domain;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherId;

    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "publisher_desc")
    private String publisherDesc;
    @Column(name = "publisher_logo")
    private String publisherLogo;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
    private List<Book> books;

}
