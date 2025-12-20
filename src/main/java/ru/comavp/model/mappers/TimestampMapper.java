package ru.comavp.model.mappers;

import org.mapstruct.Mapper;
import ru.comavp.model.dto.TimestampDto;
import ru.comavp.model.entity.TimestampEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimestampMapper {

    TimestampEntity toTimestampEntity(TimestampDto timestampDto);
    TimestampDto toTimestampDto(TimestampEntity timestampEntity);
    List<TimestampDto> toTimestampDtoList(List<TimestampEntity> timestampEntityList);
}
