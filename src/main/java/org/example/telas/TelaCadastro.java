package org.example.telas;

import org.example.model.Usuario;
import org.example.service.Autenticacao;

import javax.swing.*;

public class TelaCadastro extends JFrame {
    public TelaCadastro() {
        setTitle("Cadastro");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton registerButton = new JButton("Cadastrar");
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });


        registerButton.addActionListener(e ->
        {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            Usuario newUsuario = new Usuario(email, password);
            if (Autenticacao.registerUser(newUsuario))
            {
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                dispose();
                new TelaLogin();
            } else
            {
                JOptionPane.showMessageDialog(this, "Este e-mail já está cadastrado.");
            }
        });


        JPanel panel = new JPanel();
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Senha:"));
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(registerButton);
        panel.add(backButton);


        add(panel);
        setVisible(true);
    }
}
