package org.example.todoappneuefische.service;

import org.example.todoappneuefische.dto.ToDoDtoInput;
import org.example.todoappneuefische.dto.ToDoDtoOutput;
import org.example.todoappneuefische.model.ToDo;
import org.example.todoappneuefische.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final IdService idService;


    public ToDoService(ToDoRepository toDoRepository, IdService idService) {
        this.toDoRepository = toDoRepository;
        this.idService = idService;
    }


    public List<ToDo> getAllToDos(){
        return toDoRepository.findAll();
    }

    public ToDo getToDoById(String id){
        return toDoRepository.findById(id).orElse(null);
    }

    public ToDo addToDo(ToDoDtoInput toDoDto) {
        ToDo newToDo = new ToDo(
                idService.generateRandomId(),
                toDoDto.description(),
                toDoDto.status()
        );
        toDoRepository.save(newToDo);
        return newToDo;
    }

    public ToDo updateToDoById(ToDoDtoOutput toDo, String id) {
        return toDoRepository.findById(id)
                .map(currentToDo -> {
                    ToDo updated = currentToDo
                            .withDescription(toDo.description())
                            .withStatus(toDo.status());
                    return toDoRepository.save(updated);
                }).orElse(null);
    }

    public void deleteToDoById(String id) {
        /*
         ToDo deletedTodo = repo.FindByID(id).orElseThrow();
         toDoRepository.deleteById(id);
         return deletedToDo;
        */
        toDoRepository.deleteById(id);
    }
}
