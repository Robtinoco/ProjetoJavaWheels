package org.example.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;

public class PdfService {
    public static File generateReceipt(String email, String bike, int hours, double total, String observacao) {
        try {
            String path =  "recibo_" + System.currentTimeMillis() + ".pdf";
            File file = new File(path);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph titulo = new Paragraph("RECIBO DE ALUGUEL DE BICICLETA", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);


            Font corpo = new Font(Font.FontFamily.HELVETICA, 12);
            document.add(new Paragraph("Cliente: " + email, corpo));
            document.add(new Paragraph("Bicicleta: " + bike, corpo));
            document.add(new Paragraph(" ", corpo));


            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);


            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            BaseColor azulClaro = new BaseColor(0, 121, 184);

            PdfPCell celulaHeader1 = new PdfPCell(new Phrase("Item", headerFont));
            celulaHeader1.setBackgroundColor(azulClaro);
            celulaHeader1.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell celulaHeader2 = new PdfPCell(new Phrase("Valor", headerFont));
            celulaHeader2.setBackgroundColor(azulClaro);
            celulaHeader2.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(celulaHeader1);
            table.addCell(celulaHeader2);


            table.addCell("Duração");
            table.addCell(hours + " hora(s)");

            table.addCell("Total");
            table.addCell("R$ " + String.format("%.2f", total));

            table.addCell("Observações");
            table.addCell(observacao.isBlank() ? "Nenhuma" : observacao);

            document.add(table);


            document.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
