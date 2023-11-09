package com.example.sharemind.email.application;


import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.email.application.content.EmailTypes;

public interface EmailService {
    void sendEmailToCustomer(Consult consult, EmailTypes type);

    void sendEmailToCounselor(Consult consult, EmailTypes type);
}
