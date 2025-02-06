package com.reksoft.module1rest.persistance.repository.jpa;

import com.reksoft.module1rest.persistance.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
