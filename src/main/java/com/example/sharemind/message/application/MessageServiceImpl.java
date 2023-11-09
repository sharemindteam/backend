package com.example.sharemind.message.application;

import static com.example.sharemind.email.application.content.EmailTypes.FIRST_APPLY;
import static com.example.sharemind.email.application.content.EmailTypes.FIRST_REPLY;
import static com.example.sharemind.email.application.content.EmailTypes.SECOND_APPLY;
import static com.example.sharemind.email.application.content.EmailTypes.SECOND_REPLY;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.email.application.EmailService;
import com.example.sharemind.message.domain.Message;
import com.example.sharemind.message.dto.request.MessageRequest;
import com.example.sharemind.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConsultRepository consultRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void saveMessage(MessageRequest messageRequest) {
        Consult consult = consultRepository.findByConsultId(messageRequest.getConsultId())
                .orElseThrow(() -> new ConsultNotFoundException(messageRequest.getConsultId()));
        boolean isCustomerMessageExists = messageRepository.existsByConsultAndIsCustomer(consult, true);
        boolean isCounselorMessageExists = messageRepository.existsByConsultAndIsCustomer(consult, false);

        Message message = Message.builder()
                .consult(consult)
                .isCustomer(messageRequest.getIsCustomer())
                .content(messageRequest.getContent())
                .build();

        if (messageRequest.getIsCustomer()) {
            if (isCustomerMessageExists) {
                emailService.sendEmailToCounselor(consult, SECOND_APPLY);
            } else {
                emailService.sendEmailToCounselor(consult, FIRST_APPLY);
            }
        } else {
            if (isCounselorMessageExists) {
                emailService.sendEmailToCustomer(consult, SECOND_REPLY);
            } else {
                emailService.sendEmailToCustomer(consult, FIRST_REPLY);
            }
        }
        messageRepository.save(message);
    }
}
