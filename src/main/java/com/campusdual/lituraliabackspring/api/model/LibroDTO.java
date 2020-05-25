package com.campusdual.lituraliabackspring.api.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LibroDTO {

    private Long idLibro;
    private String isbn;
    private String titulo;
    private String sinopsis;
    private LocalDate fechaPublicacion;
    private byte[] portada;
    private int idEditorial;
}
