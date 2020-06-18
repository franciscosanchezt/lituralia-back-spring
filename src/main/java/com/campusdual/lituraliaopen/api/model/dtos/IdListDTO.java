package com.campusdual.lituraliaopen.api.model.dtos;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class IdListDTO<E, K> {

    private List<K> idsList;

    public IdListDTO(List<E> enitytList, Function<E, K> function) {
        this.idsList = enitytList.stream().map(function).collect(Collectors.toList());
    }

}
