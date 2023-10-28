package com.example.sharemind.consult.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.dto.request.CreateConsultRequest;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.counselor.exception.CounselorNotFoundException;
import com.example.sharemind.counselor.repository.CounselorRepository;
import com.example.sharemind.customer.domain.Customer;
import com.example.sharemind.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final CustomerRepository customerRepository;
    private final CounselorRepository counselorRepository;
    private final ConsultRepository consultRepository;
    private final EmailService emailService;

    @Override
    public UUID createConsult(CreateConsultRequest createConsultRequest) {

        Counselor counselor = counselorRepository.findById(createConsultRequest.getCounselorId())
                .orElseThrow(() -> new CounselorNotFoundException(createConsultRequest.getCounselorId()));

        Customer customer = customerRepository.save(createConsultRequest.toCustomer());

        Consult consult = consultRepository.save(createConsultRequest.toConsult(customer, counselor));

        return consult.getConsultUuid();
    }

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
