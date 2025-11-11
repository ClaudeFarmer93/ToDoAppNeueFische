package org.example.todoappneuefische.model;
import java.util.Collections;
import java.util.List;

public record ChatGptRequest(String model,
                             List<ChatGptMessage> messages) {
    public ChatGptRequest(String message) {
        this("gpt-4o-mini", List.of(new ChatGptMessage("user", message)));
    }
}
