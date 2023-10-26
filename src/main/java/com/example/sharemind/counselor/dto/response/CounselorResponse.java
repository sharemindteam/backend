package com.example.sharemind.counselor.dto.response;

import com.example.sharemind.counselor.domain.Counselor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CounselorResponse {

    private final Long counselorId;

    private final String nickname;

    @Builder
    public CounselorResponse(Long counselorId, String nickname) {
        this.counselorId = counselorId;
        this.nickname = nickname;
    }

    public static CounselorResponse from(Counselor counselor) {
        return CounselorResponse.builder()
                .counselorId(counselor.getCounselorId())
                .nickname(counselor.getNickname())
                .build();
    }
}
