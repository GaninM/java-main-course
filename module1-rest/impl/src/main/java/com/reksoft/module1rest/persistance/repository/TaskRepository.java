package com.reksoft.module1rest.persistance.repository;

import com.reksoft.module1rest.exception.NotFoundException;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.repository.jpa.TaskJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskRepository {

  private final TaskJpaRepository taskJpaRepository;

  public TaskEntity findById(Long id) {
    return taskJpaRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  public List<TaskEntity> findAll() {
    return taskJpaRepository.findAll();
  }

  public TaskEntity save(TaskEntity user) {
    return taskJpaRepository.save(user);
  }

  public void deleteById(Long id) {
    taskJpaRepository.deleteById(id);
  }
}
