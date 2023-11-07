package com.example.sharemind.consult.dto.response;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.message.dto.response.MessageResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetConsultResponse {

    private final Long consultId;

    private final Boolean loginByCustomer;
    private final String customerNickname;
    private final String counselorNickname;
    private final List<MessageResponse> messageResponses;

    @Builder
    public GetConsultResponse(Long consultId, Boolean loginByCustomer, String customerNickname, String counselorNickname, List<MessageResponse> messageResponses) {
        this.consultId = consultId;
        this.loginByCustomer = loginByCustomer;
        this.customerNickname = customerNickname;
        this.counselorNickname = counselorNickname;
        this.messageResponses = messageResponses;
    }

    public static GetConsultResponse from(Boolean loginByCustomer, Consult consult, List<MessageResponse> messageResponses) {
        return GetConsultResponse.builder()
                .consultId(consult.getConsultId())
                .loginByCustomer(loginByCustomer)
                .customerNickname(consult.getCustomer().getNickname())
                .counselorNickname(consult.getCounselor().getNickname())
                .messageResponses(messageResponses)
                .build();
    }
}
