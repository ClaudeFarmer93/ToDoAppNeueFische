package org.example.todoappneuefische.controller;

import org.example.todoappneuefische.service.ChatGptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatGptController {

    private final ChatGptService chatGPTService;

    public ChatGptController(ChatGptService chatGPTService) {
        this.chatGPTService = chatGPTService;

    }

    @GetMapping("api/open")
    public String checkDescription(@RequestParam String description) {
        return chatGPTService.checkDescriptionForMispells(description);
    }
}
