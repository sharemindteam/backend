package com.example.sharemind.admin.application;

import com.example.sharemind.admin.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {

    List<AdminResponse> getConsultsByIsPayFalse(String password);

    void updateIsPayTrue(Long consultId);
}
