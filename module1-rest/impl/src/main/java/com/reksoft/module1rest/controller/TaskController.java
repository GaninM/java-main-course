package com.reksoft.module1rest.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.reksoft.module1rest.persistance.repository.TaskRepository;
import com.reksoft.module1rest.service.TaskService;
import com.reksoft.user.task.api.TaskApi;
import com.reksoft.user.task.api.model.TaskDto;
import com.reksoft.user.task.api.model.TaskResponseDto;
import com.reksoft.user.task.api.model.UpdateTaskRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

  private final TaskService taskService;

  @Override
  public ResponseEntity<TaskResponseDto> createTask(TaskDto request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(request));
  }

  @Override
  public ResponseEntity<Void> deleteTaskById(Long id) {
    taskService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
    return ResponseEntity.ok(taskService.findAll());
  }

  @Override
  public ResponseEntity<TaskResponseDto> getTaskById(Long id) {
    return ResponseEntity.ok(taskService.findById(id));
  }

  @Override
  public ResponseEntity<TaskResponseDto> updateTask(UpdateTaskRequestDto request) {
    return ResponseEntity.ok(taskService.update(request));
  }

  //Ниже приведены два примера использования Spring HATEOAS
  @Operation(
      operationId = "getUserByIdHATEUS",
      summary = "Получение данных о задаче с использованием Spring HATEOAS"
  )
  @GetMapping(
      value = "/api/v1/task/{id}/hateoas",
      produces = {"application/json"}
  )
  public ResponseEntity<EntityModel<TaskResponseDto>> getTaskByIdHATEUS(@PathVariable("id") Long id) {
    TaskResponseDto user = taskService.findById(id);

    return ResponseEntity.ok(EntityModel.of(user,
        linkTo(methodOn(TaskController.class).getTaskById(id)).withSelfRel(),
        linkTo(methodOn(TaskController.class).getAllTasks()).withSelfRel())
    );
  }

  @Operation(
      operationId = "getAllTasksHATEUS",
      summary = "Получение данных о задачах с использованием Spring HATEOAS"
  )
  @GetMapping(
      value = "/api/v1/task/hateoas",
      produces = {"application/json"}
  )
  public List<EntityModel<TaskResponseDto>> getAllTasksHATEUS() {
    return taskService.findAll().stream()
        .map(user -> EntityModel.of(user,
            linkTo(methodOn(TaskController.class).getTaskById(user.getId())).withSelfRel(),
            linkTo(methodOn(TaskController.class).getAllTasks()).withSelfRel()))
        .toList();
  }
}
