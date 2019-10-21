package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User getUserByUsername(String username);

    User getUserById(long Id);


}
