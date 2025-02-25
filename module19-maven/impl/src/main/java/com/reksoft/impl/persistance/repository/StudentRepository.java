package com.reksoft.impl.persistance.repository;

import com.reksoft.impl.BaseException;
import com.reksoft.impl.persistance.model.StudentEntity;
import com.reksoft.impl.persistance.repository.jpa.StudentJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRepository {

  private final StudentJpaRepository studentJpaRepository;

  public StudentEntity findById(Long id) {
    return studentJpaRepository.findById(id)
        .orElseThrow(() -> new BaseException(String.format("Student with id %s not found", id)));
  }

  public List<StudentEntity> findAll() {
    return studentJpaRepository.findAll();
  }

  public StudentEntity save(StudentEntity entity) {
    return studentJpaRepository.save(entity);
  }

  public void deleteById(Long id) {
    studentJpaRepository.deleteById(id);
  }
}
