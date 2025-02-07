package com.reksoft.module1rest.mapper.soap;

import com.example.soap.users.CreateUserRequest;
import com.example.soap.users.Task;
import com.example.soap.users.User;
import com.reksoft.module1rest.mapper.MapperConfig;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.user.task.api.model.TaskDto;
import com.reksoft.user.task.api.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserSoapMapper {

  User map(UserEntity source);

  @Mapping(target = "id", ignore = true)
  User map(UserResponseDto source);

  @Mapping(target = "id", ignore = true)
  Task map(TaskDto source);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "tasks", ignore = true)
  UserEntity mapSoap(CreateUserRequest source);
}
