package com.example.sharemind.message.dto.request;

import lombok.Getter;

@Getter
public class MessageRequest {
    private Long consultId;
    private Boolean isCustomer;
    private String content;
}
