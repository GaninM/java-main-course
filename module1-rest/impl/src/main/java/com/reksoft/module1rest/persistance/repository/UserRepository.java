package com.reksoft.module1rest.persistance.repository;

import com.reksoft.module1rest.exception.NotFoundException;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.jpa.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRepository {

  private final UserJpaRepository userJpaRepository;

  public UserEntity findById(Long id) {
    return userJpaRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  public List<UserEntity> findAll() {
    return userJpaRepository.findAll();
  }

  public UserEntity save(UserEntity user) {
    return userJpaRepository.save(user);
  }

  public void deleteById(Long id) {
    userJpaRepository.deleteById(id);
  }
}
