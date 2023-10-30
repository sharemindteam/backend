package com.example.sharemind.consult.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final ConsultRepository consultRepository;
    private final JavaMailSender mailSender;
    @Value("${server.url}")
    private String serverUrl;

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public String setText(String text, String password, UUID uuid) {
        String link = serverUrl + "/" + uuid.toString();
        return text + "상담 링크 : \n"
                + link
                + "\n\n링크 비밀번호: " + password;
    }


    public void sendConsultationLink(UUID consult_uuid) {
        Consult consult = consultRepository.findByConsultUuid(consult_uuid)
                .orElseThrow(() -> new ConsultNotFoundException(consult_uuid));
        sendEmailToCustomer(consult, "상담 작성용 링크입니다.", "[sharemind]상담 링크입니다.");
    }

    public void notifyConsultationApply(UUID consult_uuid) {
        Consult consult = consultRepository.findByConsultUuid(consult_uuid)
                .orElseThrow(() -> new ConsultNotFoundException(consult_uuid));
        sendEmailToCustomer(consult, "상담 신청이 완료되었습니다.", "[sharemind]상담 신청이 완료되었습니다.");
        sendEmailToCounselor(consult, "새로운 상담 신청이 있습니다.", "[sharemind]새로운 상담 신청이 있습니다.");
    }

    public void notifyConsultationReply(UUID consult_uuid) {
        Consult consult = consultRepository.findByConsultUuid(consult_uuid)
                .orElseThrow(() -> new ConsultNotFoundException(consult_uuid));
        sendEmailToCustomer(consult, "상담이 응답되었습니다.", "[sharemind]상담 응답 링크입니다.");
    }

    private void sendEmailToCustomer(Consult consult, String text, String subject) {
        String emailContent = setText(text, consult.getPassword(), consult.getConsultUuid());
        sendEmail(consult.getCustomer().getEmail(), subject, emailContent);
    }

    private void sendEmailToCounselor(Consult consult, String text, String subject) {
        String emailContent = setText(text, consult.getPassword(), consult.getConsultUuid());
        sendEmail(consult.getCounselor().getEmail(), subject, emailContent);
    }
}
