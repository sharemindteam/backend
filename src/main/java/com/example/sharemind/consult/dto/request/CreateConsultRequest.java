package com.example.sharemind.consult.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateConsultRequest {

    private Long counselorId;
    
    @NotBlank
    @Email
    private String email;
}
