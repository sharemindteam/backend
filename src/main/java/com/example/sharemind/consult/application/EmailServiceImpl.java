package com.example.sharemind.consult.application;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    @Value("${server.url}")
    private String serverUrl;
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    public String setText(String text, String password, UUID uuid){
        String link = serverUrl + "/" + uuid.toString();
        return text + "상담 링크 : \n"
                + link
                + "\n\n링크 비밀번호: " + password;
    }
}

