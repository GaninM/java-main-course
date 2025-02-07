package com.reksoft.module1rest.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.reksoft.module1rest.service.rest.UserService;
import com.reksoft.module1rest.service.rest.UserTaskService;
import com.reksoft.user.task.api.UserApi;
import com.reksoft.user.task.api.model.AddUserTaskRequestDto;
import com.reksoft.user.task.api.model.UpdateUserRequestDto;
import com.reksoft.user.task.api.model.UserDto;
import com.reksoft.user.task.api.model.UserResponseDto;
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
public class UserController implements UserApi {

  private final UserService userService;
  private final UserTaskService userTaskService;

  @Override
  public ResponseEntity<UserResponseDto> addTaskToUser(
      AddUserTaskRequestDto request) {
    return ResponseEntity.ok(userTaskService.addTask(request));
  }

  @Override
  public ResponseEntity<UserResponseDto> createUser(UserDto request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
  }

  @Override
  public ResponseEntity<Void> deleteUserById(Long id) {
    userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @Override
  public ResponseEntity<UserResponseDto> getUserById(Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @Override
  public ResponseEntity<UserResponseDto> updateUser(UpdateUserRequestDto request) {
    return ResponseEntity.ok(userService.update(request));
  }

  //Ниже приведены два примера использования Spring HATEOAS
  @Operation(
      operationId = "getUserByIdHATEUS",
      summary = "Получение данных о пользователе с использованием Spring HATEOAS"
  )
  @GetMapping(
      value = "/api/v1/user/{id}/hateoas",
      produces = {"application/json"}
  )
  public ResponseEntity<EntityModel<UserResponseDto>> getUserByIdHATEUS(@PathVariable("id") Long id) {
    UserResponseDto user = userService.findById(id);

    return ResponseEntity.ok(EntityModel.of(user,
            linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel(),
            linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel())
    );
  }

  @Operation(
      operationId = "getAllUsersHATEUS",
      summary = "Получение данных о пользователях с использованием Spring HATEOAS"
  )
  @GetMapping(
      value = "/api/v1/user/hateoas",
      produces = {"application/json"}
  )
  public List<EntityModel<UserResponseDto>> getAllUsersHATEUS() {
    return userService.findAll().stream()
        .map(user -> EntityModel.of(user,
            linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel()))
        .toList();
  }
}
