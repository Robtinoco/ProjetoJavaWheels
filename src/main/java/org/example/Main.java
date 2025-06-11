package org.example;

import org.example.telas.TelaLogin;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
