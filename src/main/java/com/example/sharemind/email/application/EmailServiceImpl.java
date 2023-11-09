package com.example.sharemind.email.application;

import com.example.sharemind.consult.application.ConsultService;
import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.email.application.content.EmailContent;
import com.example.sharemind.email.application.content.EmailTypes;
import com.example.sharemind.message.dto.response.MessageResponse;
import com.example.sharemind.message.repository.MessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final MessageRepository messageRepository;
    private final ConsultService consultService;
    private final JavaMailSender mailSender;
    private final EmailContent emailContent;

    private String[] getCustomerContent(Consult consult, EmailTypes type) {
        return switch (type) {
            case LINK -> emailContent.getLinkContent(consult);
            case FIRST_REPLY -> emailContent.getFirstReplyContent(consult);
            case SECOND_REPLY -> {
                List<MessageResponse> messageResponses = messageRepository.findAllByConsult(consult)
                        .stream()
                        .map(MessageResponse::from)
                        .toList();
                yield emailContent.getSecondReplyContent(consult, messageResponses);
            }
            case CUSTOMER_NO_ADDITIONAL_QUESTION -> {
                List<MessageResponse> messageResponses = messageRepository.findAllByConsult(consult)
                        .stream()
                        .map(MessageResponse::from)
                        .toList();
                yield emailContent.getNoAdditionalQuestionCustomerContent(consult, messageResponses);
            }
            case CUSTOMER_CANCEL -> emailContent.getCancelCustomerContent(consult);
            default -> throw new IllegalArgumentException("Invalid EmailTypes: " + type);
        };
    }

    private String[] getCounselorContent(Consult consult, EmailTypes type) {
        return switch (type) {
            case FIRST_APPLY -> emailContent.getFirstApplyContent(consult);
            case SECOND_APPLY -> emailContent.getSecondApplyContent(consult);
            case COUNSELOR_NO_ADDITIONAL_QUESTION -> emailContent.getNoAdditionalQuestionCounselorContent(consult);
            case COUNSELOR_CLOSURE -> emailContent.getClosureCounselorContent(consult);
            case COUNSELOR_CANCEL -> emailContent.getCancelCounselorContent(consult);
            default -> throw new IllegalArgumentException("Invalid EmailTypes: " + type);
        };
    }

    public void sendEmailToCustomer(Long consultId, EmailTypes type) {
        Consult consult = consultService.getConsult(consultId);
        String[] content = getCustomerContent(consult, type);
        sendEmail(consult.getCustomer().getEmail(), content[0], content[1]);
    }

    public void sendEmailToCounselor(Long consultId, EmailTypes type) {
        Consult consult = consultService.getConsult(consultId);
        String[] content = getCounselorContent(consult, type);
        sendEmail(consult.getCounselor().getEmail(), content[0], content[1]);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
