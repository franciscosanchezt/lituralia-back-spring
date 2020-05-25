create table libros
(
    id_libro          IDENTITY not null
        constraint libros_pk
            primary key,
    isbn              varchar(13),
    titulo            varchar,
    sinopsis          varchar,
    fecha_publicacion datetime,
    portada           text,
    id_editorial      integer
);
