package com.example.sharemind.message.presentation;

import com.example.sharemind.message.application.MessageService;
import com.example.sharemind.message.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/messages")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> createMessage(@RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
