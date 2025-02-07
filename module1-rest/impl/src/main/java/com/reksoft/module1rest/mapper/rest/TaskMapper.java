package com.reksoft.module1rest.mapper.rest;

import com.reksoft.module1rest.mapper.MapperConfig;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.user.task.api.model.CreateTaskRequestDto;
import com.reksoft.user.task.api.model.TaskResponseDto;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "source.name")
  @Mapping(target = "description", source = "source.description")
  @Mapping(target = "creationDate", source = "creationDate")
  @Mapping(target = "deadLine", source = "source.deadLine")
  @Mapping(target = "user", source = "user")
  TaskEntity map(CreateTaskRequestDto source, UserEntity user, LocalDate creationDate);

  @Mapping(target = "userId", source = "source.user.id")
  TaskResponseDto map(TaskEntity source);
}
