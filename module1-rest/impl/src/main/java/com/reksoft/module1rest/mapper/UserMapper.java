package com.reksoft.module1rest.mapper;

import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.user.task.api.model.UserDto;
import com.reksoft.user.task.api.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "tasks", ignore = true)
  UserEntity map(UserDto source);

  UserResponseDto map(UserEntity source);
}
