package com.example.sharemind.consult.presentation;

import com.example.sharemind.consult.application.ConsultService;
import com.example.sharemind.consult.dto.request.CreateConsultRequest;
import com.example.sharemind.consult.dto.request.GetConsultRequest;
import com.example.sharemind.consult.dto.response.GetConsultResponse;
import com.example.sharemind.email.exception.InvalidEmailException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v0/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final ConsultService consultService;

    @PostMapping
    public ResponseEntity<Void> createConsult(@Valid @RequestBody CreateConsultRequest createConsultRequest,
                                              Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidEmailException(createConsultRequest.getEmail());
        }
        consultService.createConsult(createConsultRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{consultUuid}")
    public ResponseEntity<GetConsultResponse> getConsult(@PathVariable UUID consultUuid,
                                                         @RequestBody GetConsultRequest getConsultRequest) {
        return ResponseEntity.ok(consultService.getConsult(consultUuid, getConsultRequest));
    }
}
