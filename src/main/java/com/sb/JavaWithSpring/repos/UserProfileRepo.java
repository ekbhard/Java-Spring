package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

//    @Query(value ="select * from public.user_profile where id_user = ?1", nativeQuery = true)
//    UserProfile findCurrentUserProfile(Long id);

    @Query("select up from UserProfile up where up.user = ?1")
    UserProfile findCurrentUserProfile(Long id);

}
