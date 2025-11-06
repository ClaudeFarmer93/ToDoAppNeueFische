package org.example.todoappneuefische.service;

import org.example.todoappneuefische.dto.ToDoDtoInput;
import org.example.todoappneuefische.dto.ToDoDtoOutput;
import org.example.todoappneuefische.enums.Status;
import org.example.todoappneuefische.model.ToDo;
import org.example.todoappneuefische.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllToDos_shouldReturnAllToDos() {
        ToDo toDo1 = new ToDo("1", "Setup Tests", Status.DONE);
        ToDo toDo2 = new ToDo("3", "Write tests", Status.IN_PROGRESS);

        ToDoRepository mockRepo = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);

        ToDoService toDoService = new ToDoService(mockRepo,mockIdService);

        when(mockRepo.findAll()).thenReturn(List.of(toDo1,toDo2));

        List<ToDo> actual = toDoService.getAllToDos();
        assertEquals(2, actual.size());
        assertEquals("Setup Tests", actual.get(0).description());
        assertEquals("Write tests", actual.get(1).description());



    }

    @Test
    void getToDoById_shouldReturnToDoWithId() {
        ToDo toDo1 = new ToDo("1", "Setup Tests", Status.OPEN);
        ToDoRepository mockRepo = mock(ToDoRepository.class);
      //  IdService mockIdService = mock(IdService.class);
        when(mockRepo.findById("1")).thenReturn(Optional.of(toDo1));
        ToDoService toDoService = new ToDoService(mockRepo,null);

        ToDo result = toDoService.getToDoById("1");

        assertEquals("1", result.id());
        assertEquals(toDo1, result);
    }

   @Test
   void getToDoById_shouldReturnNullWhenNoToDoExists() {
       ToDoRepository mockRepo = mock(ToDoRepository.class);
       //  IdService mockIdService = mock(IdService.class);
       when(mockRepo.findById("1")).thenReturn(Optional.empty());
       ToDoService toDoService = new ToDoService(mockRepo,null);

       ToDo result = toDoService.getToDoById("1");
       assertNull(result);
   }

    @Test
    void addToDo() {
//       ToDo toDo1 = new ToDo("1", "Setup Tests", Status.DONE);
//       ToDo toDo2 = new ToDo("3", "Write tests", Status.IN_PROGRESS);

        ToDoDtoInput input = new ToDoDtoInput("Setup Project", Status.OPEN);
        ToDo savedToDo = new ToDo("1", "Setup Project", Status.OPEN);

        ToDoRepository mockRepo = mock(ToDoRepository.class);
        IdService mockIdService = mock(IdService.class);

        ToDoService toDoService = new ToDoService(mockRepo,mockIdService);

        when(mockRepo. save(Mockito.any(ToDo.class))).thenReturn(savedToDo);
        when(mockIdService.generateRandomId()).thenReturn("1");
        ToDo actual = toDoService.addToDo(input);

        assertEquals(savedToDo, actual);
        assertEquals(Status.OPEN, actual.status());
        assertEquals("Setup Project", actual.description());

    }

    @Test
    void updateToDoById_shouldUpdateAndReturnToDo() {
        ToDo toDo1 = new ToDo("1", "Setup Tests", Status.OPEN);
        ToDo updatedToDo = new ToDo("1","Updated Setup Tests", Status.IN_PROGRESS);

        ToDoDtoOutput updateDto = new  ToDoDtoOutput("Updated Setup Tests", Status.IN_PROGRESS);

        ToDoRepository mockRepo = mock(ToDoRepository.class);
        when(mockRepo.findById("1")).thenReturn(Optional.of(toDo1));
        when(mockRepo.save(Mockito.any(ToDo.class))).thenReturn(updatedToDo);
        ToDoService toDoService = new ToDoService(mockRepo,null);

        ToDo result = toDoService.updateToDoById(updateDto, "1");

        assertEquals(updatedToDo, result);
        assertEquals(Status.IN_PROGRESS, result.status());
        assertEquals("Updated Setup Tests", result.description());

    }

    @Test
    void deleteToDoById_shouldDeleteToDo(){

        ToDoRepository mockRepo = mock(ToDoRepository.class);
        ToDoService toDoService = new ToDoService(mockRepo,null);
        toDoService.deleteToDoById("1");
        verify(mockRepo).deleteById("1");
    }
}