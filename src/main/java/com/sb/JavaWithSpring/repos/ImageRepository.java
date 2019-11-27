package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.ProfileImage;
import com.sb.JavaWithSpring.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ProfileImage, Long> {

    ProfileImage getProfileImageByUser_Id(long Id);
    ProfileImage getProfileImageById(long Id);

}
