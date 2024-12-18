package com.example.todoapispring;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private TodoService TodoService;
    private static List<Todo> todoList;
    public TodoController(@Qualifier("faketodoservice") TodoService TodoService){
        todoList=new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo1",1));
        todoList.add(new Todo(2,true,"Todo2",2));
        this.TodoService = TodoService;
    }
    @GetMapping()
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted){
        System.out.println(isCompleted+ TodoService.doSomething());
        return ResponseEntity.ok(todoList);
    }
    @PostMapping()
    public ResponseEntity<Todo> createTodos(@RequestBody Todo newTodo ){
        /*
         * We can use @ResponseStatus(HttpStatus.CREATED) for 201 created status
         */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }
    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable long todoId){
        for(Todo todo: todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        String errorMessage = "Todo with ID " + todoId + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable long todoId){
        for(Todo todo: todoList){
            if(todo.getId()==todoId) {
                todoList.remove(todo);
                return ResponseEntity.ok("Todo with ID " + todoId + " has been deleted successfully.");
            }
        }
        String errorMessage = "Todo with ID " + todoId + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @PatchMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable long todoId,@RequestBody Todo updatedFields ){
        for(Todo todo: todoList){
            if(todo.getId()==todoId) {
                todo.setId(updatedFields.getId());
                todo.setTitle(updatedFields.getTitle());
                todo.setUserId(updatedFields.getUserId());
                todo.setCompleted(updatedFields.isCompleted());
                return ResponseEntity.ok(todo);
            }
        }
        String errorMessage = "Todo with ID " + todoId + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
