package com.example.sharemind.consult.application;

import com.example.sharemind.consult.dto.request.CreateConsultRequest;

import java.util.UUID;

public interface ConsultService {

    UUID createConsult(CreateConsultRequest createConsultRequest);
}
