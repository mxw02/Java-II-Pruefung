package org.example.information;

public class InformationController {

    public static void sendWelcomeMessage() {
       String welcomeMessage = "\n\nWillkommen beim interaktiven KI-gesteuerten Quizspiel!";
       System.out.println(welcomeMessage);
       String gameInstructions = "Im folgenden werden die gleich 8 Fragen aus verschiedenen Kategorien und Schwierigkeitsstufen gestellt.";
       System.out.println(gameInstructions);
       String handleInstructions = "Du gibst deine Antwort immer dann ein, wenn du an der Reihe bist. Im Anschluss an das Spiel werden dir deine erreichten Punkte angezeigt.";
       System.out.println(handleInstructions);
       String quitGame = "Das Spiel kann mit der Eingabe 'q' beendet werden";
       System.out.println(quitGame);
       String goodLuckMessage = "Viel Erfolg beim Spiel!\n\n";
       System.out.println(goodLuckMessage);
    }

    public static void sendGameClosedMessage() {
        String gameClosedMessage = "\n\nDas Spiel wurde beendet!\n\n";
        System.out.println(gameClosedMessage);
    }

}
