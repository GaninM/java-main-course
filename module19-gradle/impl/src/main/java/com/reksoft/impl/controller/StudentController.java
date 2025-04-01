package com.reksoft.impl.controller;

import com.reksoft.api.StudentApi;
import com.reksoft.dto.StudentDto;
import com.reksoft.impl.service.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController implements StudentApi {

  private final StudentService studentService;

  @Override
  public ResponseEntity<StudentDto> findById(Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @Override
  public ResponseEntity<List<StudentDto>> findAll() {
    return ResponseEntity.ok(studentService.findAll());
  }

  @Override
  public ResponseEntity<StudentDto> save(StudentDto studentDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentDto));
  }

  @Override
  public ResponseEntity<StudentDto> update(StudentDto studentDto, Long id) {
    return ResponseEntity.ok(studentService.update(studentDto, id));
  }

  @Override
  public ResponseEntity<Void> deleteById(Long id) {
    studentService.delete(id);
    return ResponseEntity.ok().build();
  }

}
