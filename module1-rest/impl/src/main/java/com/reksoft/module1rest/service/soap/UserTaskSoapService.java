package com.reksoft.module1rest.service.soap;

import com.example.soap.users.AddTaskToUserRequest;
import com.example.soap.users.User;
import com.reksoft.module1rest.mapper.soap.UserSoapMapper;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.TaskRepository;
import com.reksoft.module1rest.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTaskSoapService {

  private final UserRepository userRepository;
  private final TaskRepository taskRepository;
  private final UserSoapMapper userMapper;

  public User addTask(AddTaskToUserRequest request) {
    UserEntity user = userRepository.findById(request.getUserId());
    TaskEntity task = taskRepository.findById(request.getTaskId());

    task.setUser(user);
    taskRepository.save(task);

    return userMapper.map(user);
  }
}
