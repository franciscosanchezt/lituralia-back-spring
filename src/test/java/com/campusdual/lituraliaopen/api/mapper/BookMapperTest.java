package com.campusdual.lituraliaopen.api.mapper;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookMapperImpl.class, PublisherMapperImpl.class, GenreMapperImpl.class, AuthorMapperImpl.class})
class BookMapperTest {

    @Autowired
    BookMapper mapper;

}