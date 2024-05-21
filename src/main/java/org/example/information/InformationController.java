package org.example.information;

public class InformationController {

    public static void sendWelcomeMessage() {
       String welcomeMessage = "Willkommen beim interaktiven KI-gesteuerten Quizspiel!";
       System.out.println(welcomeMessage);
       sendQuizControllingOptions();
    }

    public static void sendQuizControllingOptions() {
        String informations = "Du kannst das Quiz mit folgenden Tasten steuern: q (Quit) oder n (nächste Frage)";
        System.out.println(informations);
    }

}
