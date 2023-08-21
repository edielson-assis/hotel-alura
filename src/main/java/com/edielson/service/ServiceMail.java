package com.edielson.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.edielson.model.ModelMessage;

public class ServiceMail {

    private String username;
    private String password;

    private void loadEmailAndPasswordFromProperties() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/email.properties")) {
            prop.load(input);
            this.username = prop.getProperty("username");
            this.password = prop.getProperty("password");
        } catch (IOException e) {
            throw new EmailException(e.getMessage());
        }
    }

    public ModelMessage sendMain(String toEmail, String code) {
        loadEmailAndPasswordFromProperties();
        ModelMessage ms = new ModelMessage(false, "");
        String from = "*******@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Código de Verificação");
            message.setText(code);
            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            if (e.getMessage().equals("Invalid Addresses")) {
                ms.setMessage("Email Inválido");
            } else {
                ms.setMessage("Erro");
            }
        }
        return ms;
    }
}