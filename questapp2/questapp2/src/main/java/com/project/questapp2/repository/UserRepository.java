package com.project.questapp2.repository;

import com.project.questapp2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUserName(String username);
}
