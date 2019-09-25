package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
//репозитори
    List<Message> findByTag(String tag);
}
