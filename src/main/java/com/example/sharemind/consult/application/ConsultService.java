package com.example.sharemind.consult.application;
import java.util.UUID;

import com.example.sharemind.consult.dto.request.CreateConsultRequest;

public interface ConsultService {

    UUID createConsult(CreateConsultRequest createConsultRequest);

    void sendConsultationLink(UUID consult_uuid);

    void notifyConsultationApply(UUID consult_uuid);

    void notifyConsultationReply(UUID consult_uuid);
}
