package com.example.sharemind.consult.application;

import java.util.UUID;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
    String setText(String text, String password, UUID uuid);
}
