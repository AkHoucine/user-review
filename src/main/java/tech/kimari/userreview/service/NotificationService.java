package tech.kimari.userreview.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tech.kimari.userreview.entity.Validation;

@AllArgsConstructor
@Service
public class NotificationService {

    JavaMailSender javaMailSender;

    public void send(Validation validation) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-rely@tech-kimari.com");
        mailMessage.setTo(validation.getUser().getEmail());
        mailMessage.setSubject("You validation code");

        String text = String.format(
                "Hello %s,<br/>Your validation code is %s.<br/>Best regards,<br/>Tech Team",
                validation.getUser().getName(),
                validation.getCode()
        );
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }
}
