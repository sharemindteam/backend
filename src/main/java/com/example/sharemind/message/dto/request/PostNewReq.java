package com.example.sharemind.message.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PostNewReq {
    private UUID consultUuid;
    private Boolean isSender;
    private String content;
}
