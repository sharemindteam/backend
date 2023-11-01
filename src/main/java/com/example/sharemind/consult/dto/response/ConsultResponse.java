package com.example.sharemind.consult.dto.response;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.message.dto.response.MessageResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ConsultResponse {

    private final Long customerId;
    private final Long counselorId;

    private final List<MessageResponse> messageResponses;

    @Builder
    public ConsultResponse(Long customerId, Long counselorId, List<MessageResponse> messageResponses) {
        this.customerId = customerId;
        this.counselorId = counselorId;
        this.messageResponses = messageResponses;
    }

    public static ConsultResponse from(Consult consult, List<MessageResponse> messageResponses) {
        return ConsultResponse.builder()
                .customerId(consult.getCustomer().getCustomerId())
                .counselorId(consult.getCounselor().getCounselorId())
                .messageResponses(messageResponses)
                .build();
    }
}
