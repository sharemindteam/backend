package com.example.sharemind.email.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.email.application.content.EmailContent;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final ConsultRepository consultRepository;
    private final JavaMailSender mailSender;

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendConsultationLink(Long consultId) {
        Consult consult = consultRepository.findByConsultId(consultId)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));
        String[] customerEmailContent = EmailContent.getConsultationLinkContent(consult.getCustomerPassword(),
                consult.getConsultUuid());
        sendEmail(consult.getCustomer().getEmail(), customerEmailContent[0], customerEmailContent[1]);
    }

    public void notifyConsultationApply(Long consultId) {
        Consult consult = consultRepository.findByConsultId(consultId)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));
        String[] customerEmailContent = EmailContent.getConsultationApplyCustomerContent(consult.getCustomerPassword(),
                consult.getConsultUuid());
        String[] counselorEmailContent = EmailContent.getConsultationApplyCounselorContent(
                consult.getCounselorPassword(), consult.getConsultUuid());
        sendEmail(consult.getCustomer().getEmail(), customerEmailContent[0], customerEmailContent[1]);
        sendEmail(consult.getCounselor().getEmail(), counselorEmailContent[0], counselorEmailContent[1]);
    }

    public void notifyConsultationReply(Long consultId) {
        Consult consult = consultRepository.findByConsultId(consultId)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));
        String[] customerEmailContent = EmailContent.getConsultationReplyContent(consult.getCustomerPassword(),
                consult.getConsultUuid());
        sendEmail(consult.getCustomer().getEmail(), customerEmailContent[0], customerEmailContent[1]);
    }

}
