package com.example.sharemind.admin.dto.response;

import com.example.sharemind.consult.domain.Consult;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class AdminResponse {

    private final Long consultId;
    private final String customerEmail;
    private final String counselorEmail;
    private final String createdAt;

    @Builder
    public AdminResponse(Long consultId, String customerEmail, String counselorEmail, String createdAt) {
        this.consultId = consultId;
        this.customerEmail = customerEmail;
        this.counselorEmail = counselorEmail;
        this.createdAt = createdAt;
    }

    public static AdminResponse from(Consult consult) {
        return AdminResponse.builder()
                .consultId(consult.getConsultId())
                .customerEmail(consult.getCustomer().getEmail())
                .counselorEmail(consult.getCounselor().getEmail())
                .createdAt(DateTimeFormatter.ofPattern("MM-dd HH:mm").format(consult.getCreatedAt()))
                .build();
    }
}
