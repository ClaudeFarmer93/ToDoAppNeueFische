package org.example.todoappneuefische.service;

import org.example.todoappneuefische.dto.ToDoDtoInput;
import org.example.todoappneuefische.dto.ToDoDtoOutput;
import org.example.todoappneuefische.enums.Status;
import org.example.todoappneuefische.exceptions.InvalidToDoException;
import org.example.todoappneuefische.exceptions.NoChatGptServiceException;
import org.example.todoappneuefische.exceptions.ToDoNotFoundException;
import org.example.todoappneuefische.model.ToDo;
import org.example.todoappneuefische.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final IdService idService;
    private final ChatGptService chatGptService;



    public ToDoService(ToDoRepository toDoRepository, IdService idService, ChatGptService chatGptService) {
        this.toDoRepository = toDoRepository;
        this.idService = idService;
        this.chatGptService = chatGptService;
    }


    public List<ToDo> getAllToDos(){
        return toDoRepository.findAll();
    }

    public ToDo getToDoById(String id){
        return toDoRepository.findById(id).orElseThrow(() -> new ToDoNotFoundException(id));
    }

    public ToDo addToDo(ToDoDtoInput toDoDto) {
        validateTodoInput(toDoDto.description(),toDoDto.status());
        String correctedDescription = chatGptService.checkDescriptionForMispells(toDoDto.description());
        //String correctedDescription = checkDescription(toDoDto.description());
        ToDo newToDo = new ToDo(
                idService.generateRandomId(),
                correctedDescription,
                toDoDto.status()
        );
        toDoRepository.save(newToDo);
        return newToDo;
    }

    public ToDo updateToDoById(ToDoDtoOutput toDo, String id) {
        validateTodoInput(toDo.description(),toDo.status());
        return toDoRepository.findById(id)
                .map(currentToDo -> {
                    ToDo updated = currentToDo
                            .withDescription(toDo.description())
                            .withStatus(toDo.status());
                    return toDoRepository.save(updated);
                }).orElseThrow(() -> new ToDoNotFoundException(id));
    }

    public void deleteToDoById(String id) {
        /*
         ToDo deletedTodo = repo.FindByID(id).orElseThrow();
         toDoRepository.deleteById(id);
         return deletedToDo;
        */
        toDoRepository.deleteById(id);
    }

    private void validateTodoInput(String description, Status status) {
        if(description == null || description.isBlank()){
            throw new InvalidToDoException("Description is empty");
        }
        if(status == null) {
            throw new InvalidToDoException("Status is empty");
        }
    }

    private String checkDescription(String description) {
       String correctedDescription;
        try{
           correctedDescription = chatGptService.checkDescriptionForMispells(description);
        }catch (NoChatGptServiceException e){
            correctedDescription = description;
        }
        return correctedDescription;
    }

}
