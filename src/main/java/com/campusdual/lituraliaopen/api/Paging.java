package com.campusdual.lituraliaopen.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paging {

    private int pageNumber;       //current page, starts at 1
    private int numberOfPages;       //total pages
    private int pageSize;   //elements per page
    private String searchTerm;

}
