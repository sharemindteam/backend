package com.example.sharemind.consult.presentation;

import com.example.sharemind.consult.application.ConsultService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class ConsultController {
    private final ConsultService consultService;
    @GetMapping("/email/{consult_uuid}")
    public void sendEmailTest(@PathVariable UUID consult_uuid) {
        consultService.sendConsultationLink(consult_uuid);
    }
}
