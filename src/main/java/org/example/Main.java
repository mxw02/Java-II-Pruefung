package org.example;

import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ChatSession;
import org.example.chat.ChatController;
import org.example.information.InformationController;
import org.example.model.GenerativeChatModel;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // We need the ChatController to control the Chat-Flow
        ChatController chatController = new ChatController();

        // To onboard the player we use the InformationController
        InformationController.sendWelcomeMessage();


        // Creates an instance of the GenerativeChatModel, so we can chat with the AI Chat Bot
        GenerativeChatModel chatModel = new GenerativeChatModel(
            "gemini-1.0-pro-002",
            1246,
            1F,
            0.95F,
            "dsopdjk042401-pruefung",
            "europe-west3"
        );

        try {

            // The chat session for our quiz game
            ChatSession chatSession = chatModel.startChatSession();

            // Set the role of the Ai for the current chat
            GenerateContentResponse welcomeChatResponse = chatSession.sendMessage("Der folgende Chat soll ein Quizspiel sein. Du bist der Moderator. Du stellst insgesamt 8 Fragen aus verschiedenen Kategorien und Schwierigkeitsstufen. Nach diesen 8 Fragen gibst du die erreichte Punktzahl aus. Gib nun deine Begrüßungsnachricht und die 1. Frage an.");
            chatController.sendChatResponseMessage(welcomeChatResponse);

            String inputtedText = "";
            int questionsAnswered = 0;

            // We want to play until the user has all questions answered
            do {

                // Get the users input
                inputtedText = chatController.getUserResponse();

                // When the users entered a 'q' we want to quit the game
                if(chatController.checkQuitChat(inputtedText)) {
                    chatController.sendMessage("Beenden...");
                    break;
                }

                // Send users response and evaluate it
                GenerateContentResponse chatResponse = chatSession.sendMessage(inputtedText);
                chatController.sendChatResponseMessage(chatResponse);

                // User has answered a question more
                questionsAnswered++;
            } while(questionsAnswered < 8);

            InformationController.sendGameClosedMessage();

        } catch (IOException e) {
            chatController.sendMessage("Leider ist ein Fehler aufgetreten. Bitte erneut versuchen.");
            e.printStackTrace();
        }

    }
}