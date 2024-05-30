package org.example.information;

import java.util.List;

public class InformationController {

    public static List<String> instructions = List.of(
            "\n\nWillkommen beim interaktiven KI-gesteuerten Quizspiel!",
            "Im folgenden werden die gleich 8 Fragen aus verschiedenen Kategorien und Schwierigkeitsstufen gestellt.",
            "Du gibst deine Antwort immer dann ein, wenn du an der Reihe bist. Im Anschluss an das Spiel werden dir deine erreichten Punkte angezeigt.",
            "Das Spiel kann mit der Eingabe 'q' beendet werden",
            "Viel Erfolg beim Spiel!\n\n"
    );

    public static void sendWelcomeMessage() {
        instructions.forEach(
            System.out::println
        );
    }

    public static void sendGameClosedMessage() {
        String gameClosedMessage = "\n\nVielen Dank für das spielen!\nDas Spiel wurde beendet!\n\n";
        System.out.println(gameClosedMessage);
    }

}
