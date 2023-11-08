package com.example.sharemind.email.application;


public interface EmailService {

    void sendConsultationLink(Long consultId);

    void notifyConsultationApply(Long consultId);

    void notifyConsultationReply(Long consultId);
}
