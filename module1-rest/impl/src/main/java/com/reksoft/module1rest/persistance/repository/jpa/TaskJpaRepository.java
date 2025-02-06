package com.reksoft.module1rest.persistance.repository.jpa;

import com.reksoft.module1rest.persistance.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
}
