create table books
(
    book_id      integer identity,
    isbn         varchar(13),
    title        varchar,
    synopsis     varchar,
    publish_date datetime,
    cover        text,
    publisher_id integer,
    constraint books_pkey
        primary key (book_id)
);
