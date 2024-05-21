package org.example;

import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ResponseStream;
import org.example.chat.ChatController;
import org.example.model.GenerativeChatModel;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        // We need the ChatController to control the Chat-Flow
        ChatController chatController = new ChatController();


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

            // Generate a response with the prompt
            chatController.sendMessage("Enter your prompt: ");
            String prompt = scanner.nextLine();

            // Count the total tokens
            CountTokensResponse countTokensResponse = chatModel.countTokens(prompt);
            chatController.sendMessage("Tokens for your prompt: " + countTokensResponse.getTotalTokens());

            // Generate a response with the prompt
            ResponseStream<GenerateContentResponse> responseStream = chatModel.generateSingleResponse("Hello, how are you?");

            // We can send the async message directly to console
            chatController.sendStreamMessage(responseStream);

            //responseStream.stream().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}