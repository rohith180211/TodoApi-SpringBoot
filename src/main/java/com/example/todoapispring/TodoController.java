package com.example.todoapispring;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class TodoController {
    private static List<Todo> todos;
    TodoController(){
        todos=new ArrayList<>();
        todos.add(new Todo(1,false,"Todo1",1));
        todos.add(new Todo(2,true,"Todo2",2));

    }
}
