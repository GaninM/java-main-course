package com.reksoft.module1rest.controller.soap;

import static com.reksoft.module1rest.constants.SoapConstants.TASKS_NAMESPACE_URI;

import com.example.soap.tasks.CreateTaskRequest;
import com.example.soap.tasks.CreateTaskResponse;
import com.example.soap.tasks.DeleteTaskRequest;
import com.example.soap.tasks.DeleteTaskResponse;
import com.example.soap.tasks.GetAllTasksRequest;
import com.example.soap.tasks.GetAllTasksResponse;
import com.example.soap.tasks.GetTaskRequest;
import com.example.soap.tasks.GetTaskResponse;
import com.example.soap.tasks.UpdateTaskRequest;
import com.example.soap.tasks.UpdateTaskResponse;
import com.reksoft.module1rest.service.soap.TaskSoapService;
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
public class TaskSoapController {

  private final TaskSoapService taskService;

  @PayloadRoot(namespace = TASKS_NAMESPACE_URI, localPart = "getTaskRequest")
  @ResponsePayload
  @Transactional
  public GetTaskResponse getTask(@RequestPayload GetTaskRequest request) {
    var response = new GetTaskResponse();
    response.setTask(taskService.findById(request.getId()));
    return response;
  }

  @PayloadRoot(namespace = TASKS_NAMESPACE_URI, localPart = "getAllTasksRequest")
  @ResponsePayload
  @Transactional
  public GetAllTasksResponse getAllTasks(@RequestPayload GetAllTasksRequest request) {
    var response = new GetAllTasksResponse();
    response.getTasks().addAll(taskService.findAll());
    return response;
  }

  @PayloadRoot(namespace = TASKS_NAMESPACE_URI, localPart = "createTaskRequest")
  @ResponsePayload
  public CreateTaskResponse createTask(@RequestPayload CreateTaskRequest request) {
    var response = new CreateTaskResponse();
    response.setTask(taskService.save(request));
    return response;
  }

  @PayloadRoot(namespace = TASKS_NAMESPACE_URI, localPart = "deleteTaskRequest")
  @ResponsePayload
  public DeleteTaskResponse deleteTask(@RequestPayload DeleteTaskRequest request) {
    taskService.deleteById(request.getTaskId());
    return new DeleteTaskResponse();
  }

  @PayloadRoot(namespace = TASKS_NAMESPACE_URI, localPart = "updateTaskRequest")
  @ResponsePayload
  @Transactional
  public UpdateTaskResponse updateTask(@RequestPayload UpdateTaskRequest request) {
    var response = new UpdateTaskResponse();
    response.setTask(taskService.update(request));
    return response;
  }

}
