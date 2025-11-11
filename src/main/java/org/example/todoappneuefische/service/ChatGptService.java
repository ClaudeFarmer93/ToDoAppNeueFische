package org.example.todoappneuefische.service;

import org.example.todoappneuefische.model.ChatGptRequest;
import org.example.todoappneuefische.model.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ChatGptService {

    private final RestClient restClient;
    public ChatGptService(RestClient.Builder restClientBuilder, @Value("${OPEN_AI_KEY}") String openAiKey) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + openAiKey)
                .build();
    }


    public String checkDescriptionForMispells(String description){
        ChatGptRequest request =
                new ChatGptRequest("Please check the following text for errors and correct it. Only return the corrected text: " + description);
       return restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(ChatGptResponse.class)
               .getResponse();


   }
}
