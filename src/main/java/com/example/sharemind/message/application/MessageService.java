package com.example.sharemind.message.application;

import com.example.sharemind.message.dto.request.PostNewReq;

public interface MessageService {
    void saveMessage(PostNewReq postNewReq);
}

