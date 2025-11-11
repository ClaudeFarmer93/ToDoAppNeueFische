package org.example.todoappneuefische.model;

import java.util.List;

public record ChatGptResponse(List<ChatGptChoice> choices) {

    public String getResponse() {
        return choices.getFirst().message().content();
    }
}
