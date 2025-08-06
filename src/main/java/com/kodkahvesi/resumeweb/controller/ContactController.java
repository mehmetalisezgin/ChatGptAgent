package com.kodkahvesi.resumeweb.controller;

import com.kodkahvesi.resumeweb.model.ContactMessage;
import com.kodkahvesi.resumeweb.repository.ContactMessageRepository;
import com.kodkahvesi.resumeweb.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller handling the contact form. It displays the form and processes
 * submissions by validating input, persisting the message and invoking the
 * email service.
 */
@Controller
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;
    private final EmailService emailService;

    @Autowired
    public ContactController(ContactMessageRepository contactMessageRepository, EmailService emailService) {
        this.contactMessageRepository = contactMessageRepository;
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contactMessage") ContactMessage contactMessage,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }
        // save to DB
        contactMessageRepository.save(contactMessage);
        // send email
        try {
            emailService.sendContactMessage(contactMessage);
            redirectAttributes.addFlashAttribute("successMessage", "Mesajınız başarıyla gönderildi!");
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mesajınız gönderilirken bir hata oluştu.");
        }
        return "redirect:/contact";
    }
}