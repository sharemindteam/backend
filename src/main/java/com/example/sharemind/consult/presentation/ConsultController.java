package com.example.sharemind.consult.presentation;

import com.example.sharemind.consult.application.ConsultService;
import com.example.sharemind.consult.application.EmailService;
import com.example.sharemind.consult.dto.request.CreateConsultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v0/consult")
@RequiredArgsConstructor
public class ConsultController {

    private final ConsultService consultService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<UUID> createConsult(@RequestBody CreateConsultRequest createConsultRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultService.createConsult(createConsultRequest));
    }

    @GetMapping("/email/{consult_uuid}")
    public void sendEmailTest(@PathVariable UUID consult_uuid) {
        emailService.sendConsultationLink(consult_uuid);
    }
}
