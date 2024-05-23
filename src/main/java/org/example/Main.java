package org.example;

import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.ResponseStream;
import org.example.chat.ChatController;
import org.example.information.InformationController;
import org.example.model.GenerativeChatModel;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        // We need the ChatController to control the Chat-Flow
        ChatController chatController = new ChatController();

        // To onboard the player we use the InformationController
        InformationController informationController = new InformationController();
        informationController.sendWelcomeMessage();


        // Creates an instance of the GenerativeChatModel, so we can chat with the AI Chat Bot
        GenerativeChatModel chatModel = new GenerativeChatModel(
            "gemini-1.5-flash-preview-0514",
            1246,
            1F,
            0.95F,
            "dsopdjk042401-pruefung",
            "europe-west3"
        );

        try {

            // The chat session for our quiz game
            ChatSession chatSession = chatModel.startChatSession();

            String inputtedText = "";

            while(true) {
                chatController.sendMessage("Deine Nachricht: ");
                inputtedText = scanner.nextLine();
                if(inputtedText.equalsIgnoreCase("Q")) {
                    chatController.sendMessage("Beenden...");
                    break;
                }
                GenerateContentResponse chatResponse = chatSession.sendMessage(inputtedText);
                chatController.sendChatResponseMessage(chatResponse);
            }

        } catch (IOException e) {
            chatController.sendMessage("Leider ist ein Fehler aufgetreten. Bitte erneut versuchen.");
            e.printStackTrace();
        }

    }
}