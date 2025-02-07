package com.reksoft.module1rest.service.soap;

import com.example.soap.tasks.CreateTaskRequest;
import com.example.soap.tasks.Task;
import com.example.soap.tasks.UpdateTaskRequest;
import com.reksoft.module1rest.mapper.soap.TaskSoapMapper;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.model.UserEntity;
import com.reksoft.module1rest.persistance.repository.TaskRepository;
import com.reksoft.module1rest.persistance.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskSoapService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final TaskSoapMapper taskMapper;

  public Task findById(Long id) {
    return taskMapper.map(taskRepository.findById(id));
  }

  public List<Task> findAll() {
    return taskRepository.findAll().stream()
        .map(taskMapper::map)
        .toList();
  }

  public Task save(CreateTaskRequest request) {
    UserEntity user = userRepository.findById(request.getUserId());
    var taskEntity = taskRepository.save(taskMapper.map(request, user, LocalDate.now()));
    return taskMapper.map(taskEntity);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

  public Task update(UpdateTaskRequest request) {
    TaskEntity task = taskRepository.findById(request.getId());
    UserEntity user = Optional.ofNullable(request.getUserId())
        .map(userRepository::findById)
        .orElse(null);

    var deadLine = request.getDeadLine();

    task.setName(request.getName());
    task.setDescription(request.getDescription());
    task.setDeadLine(LocalDate.of(deadLine.getYear(), deadLine.getMonth(), deadLine.getDay()));
    task.setUser(user);

    return taskMapper.map(taskRepository.save(task));
  }
}
