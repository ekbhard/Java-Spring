package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
//репозиторий
}
