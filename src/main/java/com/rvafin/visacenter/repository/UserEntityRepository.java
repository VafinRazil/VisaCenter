package com.rvafin.visacenter.repository;

import com.rvafin.visacenter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
