package com.example.sharemind.consult.presentation;

import com.example.sharemind.consult.application.ConsultService;
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

    @PostMapping
    public ResponseEntity<UUID> createConsult(@RequestBody CreateConsultRequest createConsultRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultService.createConsult(createConsultRequest));
    }
}
