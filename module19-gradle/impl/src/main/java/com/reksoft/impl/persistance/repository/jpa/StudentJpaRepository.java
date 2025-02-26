package com.reksoft.impl.persistance.repository.jpa;

import com.reksoft.impl.persistance.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {
}
