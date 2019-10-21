package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

}
