package org.example.module2restful.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.module2restful.mapper.UserMapper;
import org.example.module2restful.model.UserDto;
import org.example.module2restful.repository.UserRepository;
import org.example.module2restful.repository.entity.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(userMapper::map)
        .collect(Collectors.toList());
  }

  public UserDto getUserById(Long id) {
    return userMapper.map(userRepository.findById(id));
  }

  public UserDto createUser(UserDto user) {
    return userMapper.map(userRepository.save(userMapper.map(user)));
  }

  public UserDto updateUser(Long id, UserDto user) {
    user.setId(id);
    return userMapper.map(userRepository.save(userMapper.map(user)));
  }

  public void deleteUser(Long id) {
    User user = userRepository.findById(id);
    if (user != null) {
      userRepository.delete(user);
    }
  }
}