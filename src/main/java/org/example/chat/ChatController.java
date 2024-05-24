package org.example.chat;

import com.google.cloud.vertexai.api.Candidate;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;

import java.util.List;
import java.util.Scanner;

public class ChatController {

    private Scanner scanner;

    public ChatController() {
        scanner = new Scanner(System.in);
    }

    public void sendMessage(String message) {
        System.out.print(message);
    }

    public boolean checkQuitChat(String command) {
        return command.toUpperCase().equals("Q") ? true : false;
    }

    public String getUserResponse() {
        System.out.println("Bitte gib deine Antwort ein: ");
        return scanner.next();
    }


    /**
     * @param message The Stream-message you want to display in the console
     */
    /*
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

     */

    public void sendChatResponseMessage(GenerateContentResponse response) {
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
