package com.reksoft.module1rest.mapper;

import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.user.task.api.model.TaskDto;
import com.reksoft.user.task.api.model.TaskResponseDto;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "creationDate", source = "creationDate")
  TaskEntity map(TaskDto source, LocalDate creationDate);

  @Mapping(target = "userId", source = "source.user.id")
  TaskResponseDto map(TaskEntity source);
}
