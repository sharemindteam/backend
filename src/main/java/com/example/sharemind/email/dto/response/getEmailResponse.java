package com.example.sharemind.email.dto.response;

import com.example.sharemind.message.domain.Message;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class getEmailResponse {
    private final Long id;
    private final LocalDateTime createdAt;
    private final String content;
    private final String senderNickName;

    public static getEmailResponse from(Message message, String customerNickName, String counselorNickName) {
        String senderNickName = message.getIsCustomer() ? customerNickName : counselorNickName;
        return getEmailResponse.builder()
                .id(message.getMessageId())
                .createdAt(message.getCreatedAt())
                .content(message.getContent())
                .senderNickName(senderNickName)
                .build();
    }

    @Override
    public String toString() {
        return "메세지 \n{"
                + "\n보낸 사람 닉네임 : "
                + senderNickName
                + "\n내용 : "
                + content
                + "\n보낸 시간 : "
                + createdAt
                + "\n}";
    }
}
