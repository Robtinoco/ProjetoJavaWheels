package org.example.telas;

import org.example.model.Bicicleta;
import org.example.service.EmailService;
import org.example.service.Historico;
import org.example.service.PdfService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TelaAluguel extends JFrame {

    private final String userEmail;

    public TelaAluguel(String userEmail) {
        this.userEmail = userEmail;
        setTitle("Aluguel de Bicicleta");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Bicicleta[] bicicletas = {
                new Bicicleta("Bicicleta de Trilha", "MTB 700", 15.0),
                new Bicicleta("Bicicleta de Corrida", "Speed Pro", 20.0),
                new Bicicleta("Bicicleta Elétrica", "Electra X", 25.0),
                new Bicicleta("Bicicleta Urbana", "City Ride", 10.0),
                new Bicicleta("Bicicleta Infantil", "KidFun", 8.0),
                new Bicicleta("Bicicleta com Cestinha", "Cesta Urbana", 12.0),
                new Bicicleta("Bicicleta Tandem", "DuoBike 2P", 30.0),
                new Bicicleta("Bicicleta Dobrável", "Foldable Mini", 18.0)
        };

        JComboBox<Bicicleta> bikeList = new JComboBox<>(bicicletas);
        JTextField durationField = new JTextField(4); // compacto
        JTextArea obsArea = new JTextArea(3, 20);
        JButton rentButton = new JButton("Finalizar Aluguel");
        JButton historyButton = new JButton("Ver Histórico");
        JButton logoutButton = new JButton("Sair");

        logoutButton.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });

        rentButton.addActionListener(e -> {
            Bicicleta bicicletaSelecionada = (Bicicleta) bikeList.getSelectedItem();
            int horas;

            try {
                horas = Integer.parseInt(durationField.getText().trim());
                if (horas <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Informe um número válido de horas.");
                return;
            }

            String observacoes = obsArea.getText();
            double total = bicicletaSelecionada.getPrecoHora() * horas;

            // Gera o PDF
            File pdf = PdfService.generateReceipt(
                    userEmail,
                    bicicletaSelecionada.getNome() + " - " + bicicletaSelecionada.getModelo(),
                    horas,
                    total,
                    observacoes
            );

            // Envia por e-mail
            EmailService.sendEmail(userEmail, "Recibo de Aluguel", "Seu recibo está em anexo.", pdf);

            // Salva no histórico
            Historico.saveHistory(
                    userEmail,
                    bicicletaSelecionada.getNome() + " - " + bicicletaSelecionada.getModelo(),
                    horas,
                    total,
                    observacoes
            );

            JOptionPane.showMessageDialog(this, "Recibo enviado para: " + userEmail);
        });

        historyButton.addActionListener(e -> new TelaHistorico(userEmail));

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Bicicleta:"), gbc);
        gbc.gridx = 1;
        panel.add(bikeList, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Horas de aluguel:"), gbc);
        gbc.gridx = 1;
        panel.add(durationField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Observações:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(obsArea), gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(rentButton, gbc);
        gbc.gridx = 1;
        panel.add(historyButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(logoutButton, gbc);

        add(panel);
        setVisible(true);
    }
}
