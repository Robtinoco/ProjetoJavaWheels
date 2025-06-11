package org.example.telas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TelaAdministrador extends JFrame {
    public TelaAdministrador() {
        setTitle("Painel do Administrador");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton graficoButton = new JButton("Ver Gráfico de Aluguéis");
        JButton historicoButton = new JButton("Ver Histórico Geral");
        JButton voltarButton = new JButton("Sair / Voltar ao Login");

        graficoButton.addActionListener(e -> mostrarGrafico());
        historicoButton.addActionListener(e -> mostrarHistorico());
        voltarButton.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });

        JPanel botoes = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        botoes.add(graficoButton, gbc);

        gbc.gridy++;
        botoes.add(historicoButton, gbc);

        gbc.gridy++;
        botoes.add(voltarButton, gbc);

        add(botoes, BorderLayout.CENTER);
        setVisible(true);
    }

    private void mostrarGrafico() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Bicicleta de Trilha", 12);
        dataset.setValue("Bicicleta de Corrida", 9);
        dataset.setValue("Bicicleta Elétrica", 6);
        dataset.setValue("Bicicleta Infantil", 4);
        dataset.setValue("Bicicleta Urbana", 8);
        dataset.setValue("Bicicleta com Cestinha", 5);
        dataset.setValue("Bicicleta Dobrável", 3);
        dataset.setValue("Bicicleta Tandem", 2);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Distribuição de Aluguéis por Tipo de Bicicleta",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(null);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        JFrame frame = new JFrame("Gráfico de Aluguéis");
        frame.setSize(600, 400);
        frame.add(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void mostrarHistorico() {
        JTextArea historicoArea = new JTextArea(20, 60);
        historicoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(historicoArea);

        File pasta = new File("historico");
        if (pasta.exists() && pasta.isDirectory()) {
            File[] arquivos = pasta.listFiles();
            if (arquivos != null) {
                for (File file : arquivos) {
                    if (file.isFile()) {
                        historicoArea.append("=== " + file.getName() + " ===\n");
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String linha;
                            while ((linha = br.readLine()) != null) {
                                historicoArea.append(linha + "\n");
                            }
                        } catch (Exception e) {
                            historicoArea.append("Erro ao ler " + file.getName() + "\n");
                        }
                        historicoArea.append("\n");
                    }
                }
            } else {
                historicoArea.setText("Nenhum histórico encontrado.");
            }
        } else {
            historicoArea.setText("Pasta 'historico' não encontrada.");
        }

        JFrame frame = new JFrame("Histórico Geral");
        frame.add(scroll);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
