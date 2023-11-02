package com.example.sharemind.counselor.application;

import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.counselor.dto.response.CounselorResponse;
import com.example.sharemind.counselor.exception.CounselorNotFoundException;
import com.example.sharemind.counselor.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselorServiceImpl implements CounselorService {

    private final CounselorRepository counselorRepository;

    @Override
    public CounselorResponse getCounselor(Long counselorId) {

        Counselor counselor = counselorRepository.findById(counselorId)
                .orElseThrow(() -> new CounselorNotFoundException(counselorId));

        return CounselorResponse.from(counselor);
    }
}
