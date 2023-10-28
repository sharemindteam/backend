package com.example.sharemind.message.presentation;

import com.example.sharemind.consult.application.ConsultService;
import com.example.sharemind.message.application.MessageService;
import com.example.sharemind.message.dto.request.PostNewReq;
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
    private final ConsultService consultService;
    @PostMapping
    public ResponseEntity<Void> createMessage(@RequestBody PostNewReq postNewReq){
        messageService.saveMessage(postNewReq);
        Boolean isSender = postNewReq.getIsSender();
        if (Boolean.TRUE.equals(isSender)) {
            consultService.notifyConsultationApply(postNewReq.getConsultUuid());
        } else if (Boolean.FALSE.equals(isSender)) {
            consultService.notifyConsultationReply(postNewReq.getConsultUuid());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
