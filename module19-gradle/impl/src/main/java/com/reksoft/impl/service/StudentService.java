package com.reksoft.impl.service;

import com.reksoft.dto.StudentDto;
import com.reksoft.impl.mapper.StudentMapper;
import com.reksoft.impl.persistance.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public StudentDto findById(Long id) {
    return studentMapper.map(studentRepository.findById(id));
  }

  public List<StudentDto> findAll() {
    return studentRepository.findAll().stream()
        .map(studentMapper::map)
        .toList();
  }

  public StudentDto save(StudentDto studentDto) {
    var entity = studentRepository.save(studentMapper.map(studentDto));
    return studentMapper.map(entity);
  }

  public StudentDto update(StudentDto dto, Long id) {
    var student = studentRepository.findById(id);

    student.setName(dto.name());
    student.setSurname(dto.surname());
    student.setAge(dto.age());

    return studentMapper.map(studentRepository.save(student));
  }

  public void delete(Long id) {
    studentRepository.deleteById(id);
  }
}
