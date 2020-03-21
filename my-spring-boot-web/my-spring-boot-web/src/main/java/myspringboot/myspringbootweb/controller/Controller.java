package myspringboot.myspringbootweb.controller;

import javafx.concurrent.Task;
import myspringboot.myspringbootweb.model.Family;
import myspringboot.myspringbootweb.model.TaskList;
import myspringboot.myspringbootweb.service.FamilyRepository;
import myspringboot.myspringbootweb.service.TodoListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/")
    public String index() {
        logger.info("Get request for home page");
        return "Welcome to my page!!";
    }

    @RequestMapping(value = "/members")
    public List<Family> members() {
        logger.info("Get request for all family members");
        return familyRepository.findAll();
    }

    @RequestMapping(value = "todo")
    public List<TaskList> todoList() {
        logger.info("Get request for all todo items");
        return todoListRepository.findAll();
    }
}
