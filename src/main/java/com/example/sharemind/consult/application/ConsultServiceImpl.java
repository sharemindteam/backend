package com.example.sharemind.consult.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.dto.request.CreateConsultRequest;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.counselor.exception.CounselorNotFoundException;
import com.example.sharemind.counselor.repository.CounselorRepository;
import com.example.sharemind.customer.domain.Customer;
import com.example.sharemind.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final CustomerRepository customerRepository;
    private final CounselorRepository counselorRepository;
    private final ConsultRepository consultRepository;

    @Override
    public UUID createConsult(CreateConsultRequest createConsultRequest) {

        Counselor counselor = counselorRepository.findById(createConsultRequest.getCounselorId())
                .orElseThrow(() -> new CounselorNotFoundException(createConsultRequest.getCounselorId()));

        Customer customer = customerRepository.save(createConsultRequest.toCustomer());

        Consult consult = consultRepository.save(createConsultRequest.toConsult(customer, counselor));

        return consult.getConsultUuid();
    }
}
