package com.campusdual.lituraliaopen.api.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PublisherDTO {

    private Integer publisherId;
    private String publisherName;
    private String publisherDesc;
    private String publisherLogo;


}
