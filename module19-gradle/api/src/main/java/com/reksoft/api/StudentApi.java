package com.reksoft.api;

import com.reksoft.dto.StudentDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/students")
public interface StudentApi {

  @GetMapping("/{id}")
  ResponseEntity<StudentDto> findById(@PathVariable Long id);

  @GetMapping()
  ResponseEntity<List<StudentDto>> findAll();

  @PostMapping()
  ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto);

  @PutMapping("/{id}")
  ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto, @PathVariable Long id);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteById(@PathVariable Long id);
}
