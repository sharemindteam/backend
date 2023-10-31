package com.example.sharemind.consult.application;

import java.util.UUID;

public interface EmailService {
    String setText(String text, String password, UUID uuid);

    void sendConsultationLink(UUID consult_uuid);

    void notifyConsultationApply(UUID consult_uuid);

    void notifyConsultationReply(UUID consult_uuid);
}
