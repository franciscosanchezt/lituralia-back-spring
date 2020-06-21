package com.campusdual.lituraliaopen.api.model.dtos;

import com.campusdual.lituraliaopen.api.Paging;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ListDTO<T> {

    private List<T> data;
    private Paging paging;

    public ListDTO(List<T> data) {
        this.data   = data;
        this.paging = Paging.builder()
                            .pageNumber(1)
                            .numberOfPages(1)
                            .pageSize(data.size())
                            .build();
    }
}
