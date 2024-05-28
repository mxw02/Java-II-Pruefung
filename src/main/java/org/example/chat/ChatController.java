package org.example.chat;

import com.google.cloud.vertexai.api.Candidate;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.Part;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ChatController {

    private final Scanner scanner;
    private final Stack<String> anwserStack = new Stack<>();

    /**
     * Public constructor for a new ChatController
     */
    public ChatController() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a message into the console.
     * @param message to be printed.
     */
    public void sendMessage(String message) {
        System.out.print(message);
    }

    /**
     * Check if game should be quit.
     * @param command input of user
     * @return true if game should quit else false
     */
    public boolean checkQuitChat(String command) {
        return command.equalsIgnoreCase("Q");
    }

    /**
     * Get user input
     * Pushes the current answer into the answer stack.
     * @return user input
     */
    public String getUserResponse() {
        sendMessage("\nDeine Antwort: ");
        String userAnswer = scanner.next();
        anwserStack.push(userAnswer);
        return userAnswer;
    }

    /**
     * Get the latest answer from the answer stack.
     * @return the latest answer from the answer stack.
     */
    public String getLatestAnswer() {
        return anwserStack.pop();
    }

    /**
     * Prints all answers from the answer stack to the console.
     */
    public void printAllAnswers() {
        for (String answer : anwserStack) {
            System.out.println("Antwort auf Frage " + anwserStack.indexOf(answer) + 1 + ": " + answer);
        }
    }

    /**
     * Get the oldest answer from the answer stack.
     * @return the oldest answer from the answer stack.
     */
    public String getOldestAnswer() {
        return anwserStack.getLast();
    }

    /**
     * Sends the response of chat to the console.
     * @param response of the AI chat model
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

    /**
     * Sets the context of the new chat.
     * @return The game instruction for AI
     */
    public String modelChatInstruction() {
        return "Dieser Chat soll ein Quizspiel zwischen dir und dem Nutzer sein." +
                "Du bist der Moderator und stellst 8 Fragen aus verschiedenen Kategorien und Schwierigkeitsstufen." +
                "Nach diesen 8 Fragen gibst du die erreichte Punktzahl aus." +
                "Leichte Fragen geben weniger Punkte als schwere Fragen." +
                "Deine Fragen sollst du so formulieren, dass ich mit a, b c oder d antwort kann." +
                "Starte jetzt mit dem Quiz.";
    }

}
