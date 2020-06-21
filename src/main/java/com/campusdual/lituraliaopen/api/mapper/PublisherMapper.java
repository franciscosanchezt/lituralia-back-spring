package com.campusdual.lituraliaopen.api.mapper;

import com.campusdual.lituraliaopen.api.model.dtos.PublisherDTO;
import com.campusdual.lituraliaopen.domain.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDTO publisherToPublisherDTO(Publisher publisher);

    Publisher publisherDTOToPublisher(PublisherDTO publisherDto);

    default Integer publisherToId(Publisher publisher) {
        return publisher.getPublisherId();
    }

    @Mappings({@Mapping(source = "id", target = "publisherId")})
    Publisher idToPublisher(Integer id);
}
