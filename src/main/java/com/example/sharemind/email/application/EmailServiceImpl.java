package com.example.sharemind.email.application;

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

    public String setText(String text, Long password, UUID uuid) {
        String link = serverUrl + "/" + uuid.toString();
        return text + "상담 링크 : \n"
                + link
                + "\n\n링크 비밀번호: " + password;
    }


    public void sendConsultationLink(Long consultId) {
        Consult consult = consultRepository.findByConsultId(consultId)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));
        sendEmailToCustomer(consult, "상담 작성용 링크입니다.", "[sharemind]상담 링크입니다.");
    }

    public void notifyConsultationApply(Long consultId) {
        Consult consult = consultRepository.findByConsultId(consultId)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));
        sendEmailToCustomer(consult, "상담 신청이 완료되었습니다.", "[sharemind]상담 신청이 완료되었습니다.");
        sendEmailToCounselor(consult, "새로운 상담 신청이 있습니다.", "[sharemind]새로운 상담 신청이 있습니다.");
    }

    public void notifyConsultationReply(Long consultId) {
        Consult consult = consultRepository.findByConsultId(consultId)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));
        sendEmailToCustomer(consult, "상담이 응답되었습니다.", "[sharemind]상담 응답 링크입니다.");
    }

    private void sendEmailToCustomer(Consult consult, String text, String subject) {
        String emailContent = setText(text, consult.getCustomerPassword(), consult.getConsultUuid());
        sendEmail(consult.getCustomer().getEmail(), subject, emailContent);
    }

    private void sendEmailToCounselor(Consult consult, String text, String subject) {
        String emailContent = setText(text, consult.getCounselorPassword(), consult.getConsultUuid());
        sendEmail(consult.getCounselor().getEmail(), subject, emailContent);
    }
}
