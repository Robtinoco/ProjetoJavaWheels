package org.example.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;

public class PdfService {
    public static File generateReceipt(String email, String bike, int hours, double total, String observacao) {
        try {
            String path = "recibo_" + System.currentTimeMillis() + ".pdf";
            File file = new File(path);
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("RECIBO DE ALUGUEL DE BICICLETA").setBold().setFontSize(14));
            document.add(new Paragraph("Cliente: " + email));
            document.add(new Paragraph("Bicicleta: " + bike));
            document.add(new Paragraph("Duração: " + hours + " horas"));
            document.add(new Paragraph("Total: R$" + String.format("%.2f", total)));
            document.add(new Paragraph("Observações: " + (observacao.isBlank() ? "Nenhuma" : observacao)));

            document.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
