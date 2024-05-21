package org.example;

import com.google.cloud.vertexai.api.GenerateContentResponse;
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
            System.out.println("Enter your prompt: ");
            String prompt = scanner.nextLine();
            ResponseStream<GenerateContentResponse> responseStream = chatModel.generateSingleResponse("Hello, how are you?");
            responseStream.stream().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}