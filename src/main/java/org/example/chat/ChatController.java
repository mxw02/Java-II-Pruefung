package org.example.chat;

import com.google.cloud.vertexai.api.Candidate;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;
import com.google.cloud.vertexai.generativeai.ResponseStream;

import java.util.List;

public class ChatController {

    public ChatController() {

    }

    public void sendMessage(String message) {
        System.out.println(message);
    }

    public boolean validateMessage(String message) {
        // TODO: Implements validation
        return true;
    }

    public void handleCommand(String command) {
        // TODO: Implements command handling
    }


    /**
     * @param message The Stream-message you want to display in the console
     */
    public void sendStreamMessage(ResponseStream<GenerateContentResponse> message) {
        for (GenerateContentResponse response : message) {
            if (!response.getCandidatesList().isEmpty()) {
                Candidate textResponse = response.getCandidatesList().getFirst();
                Content content = textResponse.getContent();
                List<Part> parts = content.getPartsList();
                Part part = parts.getFirst();
                String text = part.getText();
                sendMessage(text);
            }
        }
    }

}
