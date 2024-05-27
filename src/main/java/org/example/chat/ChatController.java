package org.example.chat;

import com.google.cloud.vertexai.api.Candidate;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;

import java.util.List;
import java.util.Scanner;

public class ChatController {

    private final Scanner scanner;

    public ChatController() {
        scanner = new Scanner(System.in);
    }

    public void sendMessage(String message) {
        System.out.print(message);
    }

    public boolean checkQuitChat(String command) {
        return command.equalsIgnoreCase("Q");
    }

    public String getUserResponse() {
        sendMessage("\nDeine Antwort: ");
        return scanner.next();
    }

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

    public String modelChatInstruction() {
        return "Dieser Chat soll ein Quizspiel zwischen dir und dem Nutzer sein." +
                "Du bist der Moderator und stellst 8 Fragen aus verschiedenen Kategorien und Schwierigkeitsstufen." +
                "Nach diesen 8 Fragen gibst du die erreichte Punktzahl aus." +
                "Leichte Fragen geben weniger Punkte als schwere Fragen." +
                "Deine Fragen sollst du so formulieren, dass ich mit a, b c oder d antwort kann." +
                "Starte jetzt mit dem Quiz.";
    }

}
