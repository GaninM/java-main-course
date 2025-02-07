package com.reksoft.module1rest.controller.soap;

import static com.reksoft.module1rest.constants.SoapConstants.USERS_NAMESPACE_URI;

import com.example.soap.users.AddTaskToUserRequest;
import com.example.soap.users.AddTaskToUserResponse;
import com.example.soap.users.CreateUserRequest;
import com.example.soap.users.CreateUserResponse;
import com.example.soap.users.DeleteUserRequest;
import com.example.soap.users.DeleteUserResponse;
import com.example.soap.users.GetAllUsersRequest;
import com.example.soap.users.GetAllUsersResponse;
import com.example.soap.users.GetUserRequest;
import com.example.soap.users.GetUserResponse;
import com.example.soap.users.UpdateUserRequest;
import com.example.soap.users.UpdateUserResponse;
import com.reksoft.module1rest.service.soap.UserSoapService;
import com.reksoft.module1rest.service.soap.UserTaskSoapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
@RequiredArgsConstructor
public class UserSoapController {

  private final UserSoapService userService;
  private final UserTaskSoapService userTaskService;

  @PayloadRoot(namespace = USERS_NAMESPACE_URI, localPart = "getUserRequest")
  @ResponsePayload
  @Transactional
  public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
    GetUserResponse response = new GetUserResponse();
    response.setUser(userService.findById(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = USERS_NAMESPACE_URI, localPart = "getAllUsersRequest")
  @ResponsePayload
  @Transactional
  public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
    GetAllUsersResponse response = new GetAllUsersResponse();
    response.getUsers().addAll(userService.findAll());
    return response;
  }

  @PayloadRoot(namespace = USERS_NAMESPACE_URI, localPart = "createUserRequest")
  @ResponsePayload
  public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
    CreateUserResponse response = new CreateUserResponse();
    response.setUser(userService.save(request));
    return response;
  }

  @PayloadRoot(namespace = USERS_NAMESPACE_URI, localPart = "deleteUserRequest")
  @ResponsePayload
  public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
    userService.deleteById(request.getUserId());
    return new DeleteUserResponse();
  }

  @PayloadRoot(namespace = USERS_NAMESPACE_URI, localPart = "updateUserRequest")
  @ResponsePayload
  @Transactional
  public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
    UpdateUserResponse response = new UpdateUserResponse();
    response.setUser(userService.update(request));
    return response;
  }

  @PayloadRoot(namespace = USERS_NAMESPACE_URI, localPart = "addTaskToUserRequest")
  @ResponsePayload
  @Transactional
  public AddTaskToUserResponse addTaskToUser(@RequestPayload AddTaskToUserRequest request) {
    var response = new AddTaskToUserResponse();
    response.setUser(userTaskService.addTask(request));
    return response;
  }
}
