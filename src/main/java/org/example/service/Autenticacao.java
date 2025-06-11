package org.example.service;

import org.example.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Autenticacao {

    private static final String USERS_FILE = "users.txt";

    public static boolean registerUser(Usuario usuario) {
        if (isEmailRegistered(usuario.getEmail())) {
            return false;
        }

        try (FileWriter writer = new FileWriter(USERS_FILE, true)) {
            writer.write(usuario.getEmail() + ";" + usuario.getPassword() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isEmailRegistered(String email) {
        return getUsers().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public static boolean authenticate(String email, String password) {
        return getUsers().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password));
    }

    public static List<Usuario> getUsers() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    usuarios.add(new Usuario(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
