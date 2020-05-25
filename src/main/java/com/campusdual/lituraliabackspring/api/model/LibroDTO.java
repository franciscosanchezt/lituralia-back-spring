package com.campusdual.lituraliabackspring.api.model;

import java.time.LocalDate;
import lombok.Data;

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
