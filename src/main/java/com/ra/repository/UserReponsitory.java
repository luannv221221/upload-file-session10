package com.ra.repository;

import com.ra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReponsitory extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
