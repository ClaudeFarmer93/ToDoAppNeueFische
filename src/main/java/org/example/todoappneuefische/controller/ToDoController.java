package org.example.todoappneuefische.controller;

import org.example.todoappneuefische.dto.ToDoDtoInput;
import org.example.todoappneuefische.dto.ToDoDtoOutput;
import org.example.todoappneuefische.model.ToDo;
import org.example.todoappneuefische.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @GetMapping
    public List<ToDo> getAllToDos(){
        return toDoService.getAllToDos();
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id){
        return toDoService.getToDoById(id);
    }

    @PostMapping
    public ToDo addNewToDo(@RequestBody ToDoDtoInput toDoDtoInput){
        return toDoService.addToDo(toDoDtoInput);
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@RequestBody ToDoDtoOutput toDoDtoOutput, @PathVariable String id) {
        return toDoService.updateToDoById(toDoDtoOutput, id);
    }


    @DeleteMapping("/{id}")
    public void deleteToDoById(@PathVariable String id){
        toDoService.deleteToDoById(id);
    }
}
