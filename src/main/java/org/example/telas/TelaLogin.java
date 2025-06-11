package org.example.telas;

import org.example.model.Administrador;
import org.example.service.Autenticacao;

import javax.swing.*;

public class TelaLogin extends JFrame {
    public TelaLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Entrar");
        JButton registerButton = new JButton("Cadastrar");

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            Administrador admin = new Administrador();

            if (admin.autenticar(email, password)) {
                JOptionPane.showMessageDialog(this, "Login como administrador.");
                dispose();
                new TelaAdministrador();
            } else if (Autenticacao.authenticate(email, password)) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                dispose();
                new TelaAluguel(email);
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha inválidos.");
            }
        });

        registerButton.addActionListener(e -> {
            dispose();
            new TelaCadastro();
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Senha:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }
}
