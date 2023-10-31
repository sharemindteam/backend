package com.example.sharemind.message.application;

import com.example.sharemind.message.dto.request.MessageRequest;

public interface MessageService {
    void saveMessage(MessageRequest messageRequest);
}
