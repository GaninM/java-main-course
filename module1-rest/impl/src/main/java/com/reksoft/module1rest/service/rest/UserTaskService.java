package com.reksoft.module1rest.service.rest;

import com.reksoft.module1rest.mapper.rest.UserMapper;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.TaskRepository;
import com.reksoft.module1rest.persistance.repository.UserRepository;
import com.reksoft.user.task.api.model.AddUserTaskRequestDto;
import com.reksoft.user.task.api.model.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTaskService {

  private final UserRepository userRepository;
  private final TaskRepository taskRepository;
  private final UserMapper userMapper;

  public UserResponseDto addTask(AddUserTaskRequestDto request) {
    UserEntity user = userRepository.findById(request.getUserId());
    TaskEntity task = taskRepository.findById(request.getTaskId());

    task.setUser(user);
    taskRepository.save(task);

    return userMapper.map(user);
  }
}
