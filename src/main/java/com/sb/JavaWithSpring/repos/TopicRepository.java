package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Long countTopicsByUser_Id(Long id);

    Topic findTopicById(Long id);

    List<Topic> findTopicsByCategoryOrderByCreatedDateDesc(String category);
    List<Topic> findTopicsByUser_IdOrderByCreatedDateDesc(Long id);
    List<Topic> findTopicsByCategory(String category);
    List<Topic> findTopicsByTitle(String title);
}
