package com.example.sharemind.consult.application;

import com.example.sharemind.consult.domain.Consult;
import java.util.UUID;

public interface ConsultService {
    void sendConsultationLink(UUID consult_uuid);

    void notifyConsultationApply(UUID consult_uuid);

    void notifyConsultationReply(UUID consult_uuid);

}


