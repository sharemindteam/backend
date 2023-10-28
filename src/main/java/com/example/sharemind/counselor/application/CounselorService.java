package com.example.sharemind.counselor.application;

import com.example.sharemind.counselor.dto.response.CounselorResponse;

public interface CounselorService {

    CounselorResponse getCounselor(Long counselorId);
}
