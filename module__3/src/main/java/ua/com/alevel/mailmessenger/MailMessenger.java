package ua.com.alevel.mailmessenger;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import ua.com.alevel.config.MainConfig;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailMessenger {
    public static void htmlEmailMessenger(String from, String toMail, String subject, String body) throws MessagingException {
        JavaMailSender sender = MainConfig.getMailConfig();
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper htmlMessage = new MimeMessageHelper(message, true);
        htmlMessage.setTo(toMail);
        htmlMessage.setFrom(from);
        htmlMessage.setSubject(subject);
        htmlMessage.setText(body, true);
        sender.send(message);
    }
}
