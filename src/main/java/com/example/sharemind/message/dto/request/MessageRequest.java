package com.example.sharemind.message.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class MessageRequest {
    private UUID consultUuid;
    private Boolean isCustomer;
    private String content;
}
