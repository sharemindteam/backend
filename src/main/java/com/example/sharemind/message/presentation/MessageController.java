package com.example.sharemind.message.presentation;

import com.example.sharemind.consult.application.EmailService;
import com.example.sharemind.message.application.MessageService;
import com.example.sharemind.message.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/message")
public class MessageController {
    private final MessageService messageService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> createMessage(@RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        Boolean isSender = messageRequest.getIsSender();
        if (Boolean.TRUE.equals(isSender)) {
            emailService.notifyConsultationApply(messageRequest.getConsultUuid());
        } else if (Boolean.FALSE.equals(isSender)) {
            emailService.notifyConsultationReply(messageRequest.getConsultUuid());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
