package org.example.service;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.mailer.config.TransportStrategy;


import jakarta.activation.FileDataSource;

import java.io.File;

public class EmailService {
    private static final String FROM_EMAIL = "seuEmail@gmail.com";
    private static final String PASSWORD = "sua-senha-de-aplicativo";

    public static void sendEmail(String to, String subject, String body, File attachment) {
        Email email = EmailBuilder.startingBlank()
                .from("Sistema de Aluguel", FROM_EMAIL)
                .to("Cliente", to)
                .withSubject(subject)
                .withPlainText(body)
                .withAttachment(attachment.getName(), new FileDataSource(attachment))
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, FROM_EMAIL, PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();

        mailer.sendMail(email);
    }
}

