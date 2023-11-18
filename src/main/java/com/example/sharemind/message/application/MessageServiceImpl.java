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
import com.example.sharemind.message.exception.MaxMessageExceededException;
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

    private final static int MAX_MESSAGE_COUNT = 2;

    @Override
    @Transactional
    public void saveMessage(MessageRequest messageRequest) {
        Consult consult = consultRepository.findByConsultId(messageRequest.getConsultId())
                .orElseThrow(() -> new ConsultNotFoundException(messageRequest.getConsultId()));
        int messageCount = messageRepository.countByConsultAndIsCustomer(consult, messageRequest.getIsCustomer());

        if (messageCount >= MAX_MESSAGE_COUNT) {
            throw new MaxMessageExceededException(MAX_MESSAGE_COUNT);
        }

        Message message = Message.builder()
                .consult(consult)
                .isCustomer(messageRequest.getIsCustomer())
                .content(messageRequest.getContent())
                .build();

        if (messageRequest.getIsCustomer()) {
            switch (messageCount) {
                case 0: {
                    emailService.sendEmailToCounselor(consult, FIRST_APPLY);
                    break;
                }
                case 1: {
                    emailService.sendEmailToCounselor(consult, SECOND_APPLY);
                }
            }
        } else {
            switch (messageCount) {
                case 0: {
                    emailService.sendEmailToCustomer(consult, FIRST_REPLY);
                    break;
                }
                case 1: {
                    emailService.sendEmailToCustomer(consult, SECOND_REPLY);
                }
            }
        }
        messageRepository.save(message);
    }
}
