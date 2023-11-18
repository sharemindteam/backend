package com.example.sharemind.email.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.email.application.content.EmailContent;
import com.example.sharemind.email.application.content.EmailTypes;
import com.example.sharemind.email.dto.response.getEmailResponse;
import com.example.sharemind.email.exception.InvalidEmailTypeException;
import com.example.sharemind.message.repository.MessageRepository;
import com.example.sharemind.review.domain.Review;
import com.example.sharemind.review.exception.ReviewNotFoundException;
import com.example.sharemind.review.mapper.ReviewMapper;
import com.example.sharemind.review.repository.ReviewRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final MessageRepository messageRepository;
    private final ReviewRepository reviewRepository;
    private final JavaMailSender mailSender;
    private final EmailContent emailContent;
    private final ReviewMapper reviewMapper;

    private String[] getCustomerContent(Consult consult, EmailTypes type) {
        return switch (type) {
            case LINK -> emailContent.getLinkContent(consult);
            case FIRST_REPLY -> emailContent.getFirstReplyContent(consult);
            case SECOND_REPLY -> {
                reviewRepository.save(reviewMapper.toEntity(consult));
                Review review = reviewRepository.findByConsult(consult)
                        .orElseThrow(() -> new ReviewNotFoundException(consult.getConsultId()));
                String messageString = messageRepository.findAllByConsult(consult)
                        .stream()
                        .map(message -> getEmailResponse.from(message, consult.getCustomer().getNickname(),
                                consult.getCounselor().getNickname()))
                        .map(getEmailResponse::toString)
                        .collect(Collectors.joining("\n"));
                yield emailContent.getSecondReplyContent(consult, messageString, review.getReviewUuid());
            }
            case CUSTOMER_NO_ADDITIONAL_QUESTION -> {
                reviewRepository.save(reviewMapper.toEntity(consult));
                Review review = reviewRepository.findByConsult(consult)
                        .orElseThrow(() -> new ReviewNotFoundException(consult.getConsultId()));
                String messageString = messageRepository.findAllByConsult(consult)
                        .stream()
                        .map(message -> getEmailResponse.from(message, consult.getCustomer().getNickname(),
                                consult.getCounselor().getNickname()))
                        .map(getEmailResponse::toString)
                        .collect(Collectors.joining("\n"));
                yield emailContent.getNoAdditionalQuestionCustomerContent(consult, messageString,
                        review.getReviewUuid());
            }
            case CUSTOMER_CANCEL -> emailContent.getCancelCustomerContent(consult);
            default -> throw new InvalidEmailTypeException(type);
        };
    }

    private String[] getCounselorContent(Consult consult, EmailTypes type) {
        return switch (type) {
            case FIRST_APPLY -> emailContent.getFirstApplyContent(consult);
            case SECOND_APPLY -> emailContent.getSecondApplyContent(consult);
            case COUNSELOR_NO_ADDITIONAL_QUESTION -> emailContent.getNoAdditionalQuestionCounselorContent(consult);
            case COUNSELOR_CLOSURE -> emailContent.getClosureCounselorContent(consult);
            case COUNSELOR_CANCEL -> emailContent.getCancelCounselorContent(consult);
            default -> throw new InvalidEmailTypeException(type);
        };
    }

    @Override
    public void sendEmailToCustomer(Consult consult, EmailTypes type) {
        String[] content = getCustomerContent(consult, type);
        sendEmail(consult.getCustomer().getEmail(), content[0], content[1]);
    }

    @Override
    public void sendEmailToCounselor(Consult consult, EmailTypes type) {
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
