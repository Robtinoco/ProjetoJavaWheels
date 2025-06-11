package org.example.telas;

import org.example.service.Historico;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaHistorico extends JFrame
{

    public TelaHistorico(String userEmail)
    {
        setTitle("Histórico de Aluguéis");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        List<String> historico = Historico.loadHistory(userEmail);
        if (historico.isEmpty())
        {
            historyArea.setText("Nenhum aluguel encontrado.");
        } else {
            for (String linha : historico)
            {
                historyArea.append(linha + "\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(historyArea);

        add(scrollPane);
        setVisible(true);
    }
}
