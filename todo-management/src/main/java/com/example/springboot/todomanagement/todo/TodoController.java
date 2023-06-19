package com.example.springboot.todomanagement.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    private TodoService todoService;

    @RequestMapping("list-todos")
    public String todosPage(ModelMap model){
        List<Todo> todos = todoService.findByUsername("hemant");
        model.addAttribute("todos",  todos);
        return "listTodos";
    }
}
