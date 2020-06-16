package com.campusdual.lituraliaopen.api.model;

import com.campusdual.lituraliaopen.api.Paging;
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
public class BookListDTO {

    private List<BookDTO> books = new ArrayList<>();
    private Paging paging;
}
