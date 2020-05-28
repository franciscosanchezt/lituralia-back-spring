package com.campusdual.lituraliabackspring.api.model;

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
public class LibroListDTO {

    List<LibroDTO> libros = new ArrayList<>();
}
