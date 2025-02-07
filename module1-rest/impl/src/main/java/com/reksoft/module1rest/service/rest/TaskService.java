package com.reksoft.module1rest.service.rest;

import com.reksoft.module1rest.mapper.rest.TaskMapper;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.TaskRepository;
import com.reksoft.module1rest.persistance.repository.UserRepository;
import com.reksoft.user.task.api.model.CreateTaskRequestDto;
import com.reksoft.user.task.api.model.TaskResponseDto;
import com.reksoft.user.task.api.model.UpdateTaskRequestDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  private final UserRepository userRepository;

  public TaskResponseDto findById(Long id) {
    return taskMapper.map(taskRepository.findById(id));
  }

  public List<TaskResponseDto> findAll() {
    return taskRepository.findAll().stream()
        .map(taskMapper::map)
        .toList();
  }

  public TaskResponseDto save(CreateTaskRequestDto request) {
    var user = Optional.ofNullable(request.getUserId())
        .map(userRepository::findById)
        .orElse(null);
    var taskEntity = taskRepository.save(taskMapper.map(request, user, LocalDate.now()));
    return taskMapper.map(taskEntity);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

  public TaskResponseDto update(UpdateTaskRequestDto request) {
    TaskEntity task = taskRepository.findById(request.getId());
    UserEntity user = userRepository.findById(request.getId());

    task.setName(request.getName());
    task.setDescription(request.getDescription());
    task.setDeadLine(request.getDeadLine());
    task.setUser(user);

    return taskMapper.map(taskRepository.save(task));
  }
}
