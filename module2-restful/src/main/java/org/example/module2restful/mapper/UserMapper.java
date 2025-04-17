package org.example.module2restful.mapper;

import org.example.module2restful.model.UserDto;
import org.example.module2restful.repository.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User map(UserDto dto) {
    return User.builder()
        .id(dto.getId())
        .name(dto.getName())
        .email(dto.getEmail())
        .build();
  }

  public UserDto map(User user) {
    return UserDto.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }
}
