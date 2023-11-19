package com.example.sharemind.counselor.presentation;

import com.example.sharemind.counselor.application.CounselorService;
import com.example.sharemind.counselor.dto.response.CounselorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.OPTIONS}, maxAge = 1800)
@RestController
@RequestMapping("/api/v0/counselors")
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService counselorService;

    @GetMapping("/{counselorId}")
    public ResponseEntity<CounselorResponse> getCounselor(@PathVariable Long counselorId) {
        return ResponseEntity.ok(counselorService.getCounselor(counselorId));
    }
}
