package com.example.sharemind.consult.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {
    private final ConsultRepository consultRepository;
    private final EmailService emailService;

    public void sendConsultationLink(UUID consult_uuid) {
        Consult consult = consultRepository.findByConsultUuid(consult_uuid)
                .orElseThrow(ConsultNotFoundException::new);
        sendEmailToCustomer(consult, "상담 작성용 링크입니다.", "[sharemind]상담 링크입니다.");
    }

    public void notifyConsultationApply(UUID consult_uuid) {
        Consult consult = consultRepository.findByConsultUuid(consult_uuid)
                .orElseThrow(ConsultNotFoundException::new);
        sendEmailToCustomer(consult, "상담 신청이 완료되었습니다.", "[sharemind]상담 신청이 완료되었습니다.");
        sendEmailToCounselor(consult, "새로운 상담 신청이 있습니다.", "[sharemind]새로운 상담 신청이 있습니다.");
    }

    public void notifyConsultationReply(UUID consult_uuid) {
        Consult consult = consultRepository.findByConsultUuid(consult_uuid)
                .orElseThrow(ConsultNotFoundException::new);
        sendEmailToCustomer(consult, "상담이 응답되었습니다.", "[sharemind]상담 응답 링크입니다.");
    }
    private void sendEmailToCustomer(Consult consult, String text, String subject) {
        String emailContent = emailService.setText(text, consult.getPassword(), consult.getConsultUuid());
        emailService.sendEmail(consult.getCustomer().getEmail(), subject, emailContent);
    }

    private void sendEmailToCounselor(Consult consult, String text, String subject) {
        String emailContent = emailService.setText(text, consult.getPassword(), consult.getConsultUuid());
        emailService.sendEmail(consult.getCounselor().getEmail(), subject, emailContent);
    }
}
