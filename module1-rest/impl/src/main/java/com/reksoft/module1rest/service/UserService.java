package com.reksoft.module1rest.service;

import com.reksoft.module1rest.mapper.UserMapper;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.UserRepository;
import com.reksoft.user.task.api.model.UpdateUserRequestDto;
import com.reksoft.user.task.api.model.UserDto;
import com.reksoft.user.task.api.model.UserResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserResponseDto findById(Long id) {
    return userMapper.map(userRepository.findById(id));
  }

  public List<UserResponseDto> findAll() {
    return userRepository.findAll().stream()
        .map(userMapper::map)
        .toList();
  }

  public UserResponseDto save(UserDto request) {
    var userEntity = userRepository.save(userMapper.map(request));
    return userMapper.map(userEntity);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public UserResponseDto update(UpdateUserRequestDto request) {
    UserEntity user = userRepository.findById(request.getId());

    user.setName(request.getName());
    user.setSurname(request.getSurname());
    user.setMail(request.getMail());

    return userMapper.map(userRepository.save(user));
  }
}
