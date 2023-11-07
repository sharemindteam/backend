package com.example.sharemind.email.application;

import java.util.UUID;

public interface EmailService {
    String setText(String text, Long password, UUID uuid);

    void sendConsultationLink(Long consultId);

    void notifyConsultationApply(Long consultId);

    void notifyConsultationReply(Long consultId);
}
