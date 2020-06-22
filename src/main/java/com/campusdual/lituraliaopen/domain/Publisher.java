package com.campusdual.lituraliaopen.domain;


import java.util.HashSet;
import java.util.Set;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

}
