package com.campusdual.lituraliaopen.api.model.dtos;

import com.campusdual.lituraliaopen.api.model.Paging;
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
public class ListDTO<T> {

    private List<T> data = new ArrayList<>();
    private Paging paging;
}
