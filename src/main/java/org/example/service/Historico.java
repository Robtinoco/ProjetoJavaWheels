package org.example.service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Historico {

    private static final String HISTORY_DIR = "historico/";

    static {
        File dir = new File(HISTORY_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void saveHistory(String email, String bike, int hours, double total, String obs) {
        String filename = HISTORY_DIR + email.replaceAll("@", "_at_") + "_history.txt";

        try (FileWriter writer = new FileWriter(filename, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            writer.write(String.format("Data: %s | Bicicleta: %s | Duração: %dh | Total: R$%.2f | Obs: %s%n",
                    timestamp, bike, hours, total, obs.replaceAll("\\n", " ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadHistory(String email) {
        String filename = HISTORY_DIR + email.replaceAll("@", "_at_") + "_history.txt";
        List<String> lines = new ArrayList<>();

        File file = new File(filename);
        if (!file.exists()) return lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
