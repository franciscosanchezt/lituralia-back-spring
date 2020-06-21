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

    private int pageNumber = 0;          //current page, starts at 1
    private int numberOfPages = 1;       //total pages
    private int pageSize = 0;            //elements per page
    private String searchTerm = "";

}
