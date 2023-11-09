package com.example.sharemind.email.application;


import com.example.sharemind.email.application.content.EmailTypes;

public interface EmailService {
    void sendEmailToCustomer(Long consultId, EmailTypes type);

    void sendEmailToCounselor(Long consultId, EmailTypes type);
}
