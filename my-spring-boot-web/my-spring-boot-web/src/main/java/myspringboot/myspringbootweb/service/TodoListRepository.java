package myspringboot.myspringbootweb.service;

import myspringboot.myspringbootweb.model.TaskList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoListRepository extends MongoRepository<TaskList, Long> {
}
