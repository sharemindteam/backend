package com.example.sharemind.admin.presentation;

import com.example.sharemind.admin.application.AdminService;
import com.example.sharemind.admin.dto.response.AdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v0/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getConsultsByIsPayFalse(@RequestParam String password) {
        return ResponseEntity.ok(adminService.getConsultsByIsPayFalse(password));
    }

    @PatchMapping("/{consultId}")
    public ResponseEntity<Void> updateIsPayTrue(@PathVariable Long consultId) {

        adminService.updateIsPayTrue(consultId);

        return ResponseEntity.ok().build();
    }
}
