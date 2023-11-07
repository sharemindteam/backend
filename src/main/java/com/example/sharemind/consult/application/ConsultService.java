package com.example.sharemind.consult.application;

import java.util.UUID;

import com.example.sharemind.consult.dto.request.CreateConsultRequest;
import com.example.sharemind.consult.dto.request.GetConsultRequest;
import com.example.sharemind.consult.dto.response.ConsultResponse;

public interface ConsultService {
    void createConsult(CreateConsultRequest createConsultRequest);

    ConsultResponse getConsult(UUID consultUuid, GetConsultRequest getConsultRequest);
}
