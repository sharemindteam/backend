package com.example.sharemind.admin.application;

import com.example.sharemind.admin.dto.response.AdminResponse;
import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.email.application.EmailService;
import com.example.sharemind.email.application.content.EmailTypes;
import com.example.sharemind.global.exception.IncorrectPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Value("${admin.password}")
    private String ADMIN_PASSWORD;

    private final ConsultRepository consultRepository;
    private final EmailService emailService;

    @Override
    public List<AdminResponse> getConsultsByIsPayFalse(String password) {

        if (!ADMIN_PASSWORD.equals(password)) {
            throw new IncorrectPasswordException();
        }

        List<AdminResponse> adminResponses = consultRepository.findAllByIsPayAndIsActivated(false, true)
                .stream()
                .map(AdminResponse::from)
                .toList();

        return adminResponses;
    }

    @Override
    @Transactional
    public void updateIsPayTrue(Long consultId) {

        Consult consult = consultRepository.findByConsultIdAndIsPayAndIsActivated(consultId, false, true)
                .orElseThrow(() -> new ConsultNotFoundException(consultId));

        consult.updateIsPayToTrue();

        emailService.sendEmailToCustomer(consult, EmailTypes.LINK);
    }
}
