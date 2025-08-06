package com.kodkahvesi.resumeweb.service;

import com.kodkahvesi.resumeweb.model.ContactMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service encapsulating e‑mail sending logic using Spring's JavaMailSender.
 */
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * Recipient address where contact messages should be delivered.
     */
    @Value("${mail.to.address:mehmetaliszgn@gmail.com}")
    private String toAddress;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends an email using the provided contact message. The sender of the
     * message will be set to the user's email and the recipient will be the
     * configured toAddress.
     *
     * @param message the contact message submitted by the user
     * @throws MessagingException if the message cannot be built or sent
     */
    public void sendContactMessage(ContactMessage message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setTo(toAddress);
        helper.setSubject("Yeni iletişim formu mesajı: " + message.getName());
        helper.setText("Gönderen: " + message.getName() + "\nE‑posta: " + message.getEmail() + "\n\n" + message.getMessage());
        // Use a generic from address if configured, else leave null (the mail server may supply default)
        helper.setFrom(message.getEmail());
        mailSender.send(mimeMessage);
    }
}