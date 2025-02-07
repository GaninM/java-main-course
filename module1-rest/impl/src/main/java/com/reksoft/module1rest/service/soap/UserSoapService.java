package com.reksoft.module1rest.service.soap;

import com.example.soap.users.CreateUserRequest;
import com.example.soap.users.UpdateUserRequest;
import com.example.soap.users.User;
import com.reksoft.module1rest.mapper.soap.UserSoapMapper;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSoapService {

  private final UserRepository userRepository;
  private final UserSoapMapper userMapper;

  public User findById(Long id) {
    return userMapper.map(userRepository.findById(id));
  }

  public List<User> findAll() {
    return userRepository.findAll().stream()
        .map(userMapper::map)
        .toList();
  }

  public User save(CreateUserRequest request) {
    var userEntity = userRepository.save(userMapper.mapSoap(request));
    return userMapper.map(userEntity);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public User update(UpdateUserRequest request) {
    UserEntity user = userRepository.findById(request.getId());

    user.setName(request.getName());
    user.setSurname(request.getSurname());
    user.setMail(request.getMail());

    return userMapper.map(userRepository.save(user));
  }
}
