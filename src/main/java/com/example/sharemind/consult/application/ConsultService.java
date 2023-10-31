package com.example.sharemind.consult.application;

import java.util.UUID;

import com.example.sharemind.consult.dto.request.CreateConsultRequest;

public interface ConsultService {
    UUID createConsult(CreateConsultRequest createConsultRequest);
}
