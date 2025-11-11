package org.example.todoappneuefische.controller;

import org.example.todoappneuefische.enums.Status;
import org.example.todoappneuefische.model.ToDo;
import org.example.todoappneuefische.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepository mockRepo;


    @Test
    void getAllToDos() throws Exception {
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }

    @Test
    @DirtiesContext
    void getToDoById() throws Exception{
        ToDo todo = new ToDo("1", "write Integration tests", Status.OPEN);
        mockRepo.save(todo);

        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{
"id": "1",
"description": "write Integration tests",
"status": "OPEN"
}

"""));
    }

    @Test
    @DirtiesContext
    void addNewToDo() throws Exception {
        mockMvc.perform(post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "description": "new todo",
                        "status": "OPEN"
                        }
                        """)
        )
                .andExpect(status().isOk())
                .andExpect(content().json("""
 {
                        "description": "new todo",
                        "status": "OPEN"
                        }
"""))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void updateToDo() throws Exception {
        ToDo todo = new ToDo("1", "write Integration tests", Status.OPEN);
        mockRepo.save(todo);

        mockMvc.perform(put("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "description": "write Integration tests again",
"status": "IN_PROGRESS"
}
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{
"id": "1",
"description": "write Integration tests again",
"status": "IN_PROGRESS"
}
"""));
    }

    @Test
    @DirtiesContext
    void deleteToDoById() throws Exception {
        ToDo todo = new ToDo("1", "write Integration tests", Status.OPEN);
        mockRepo.save(todo);

        mockMvc.perform(delete("/api/todo/1"))
                .andExpect(status().isOk());
    }
}