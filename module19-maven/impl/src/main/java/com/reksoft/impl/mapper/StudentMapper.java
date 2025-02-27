package com.reksoft.impl.mapper;

import com.reksoft.dto.StudentDto;
import com.reksoft.impl.persistance.model.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

  public StudentDto map(StudentEntity source) {
    return new StudentDto(source.getName(), source.getSurname(), source.getAge());
  }

  public StudentEntity map(StudentDto source) {
    return StudentEntity.builder()
        .name(source.name())
        .surname(source.surname())
        .age(source.age())
        .build();
  }
}
