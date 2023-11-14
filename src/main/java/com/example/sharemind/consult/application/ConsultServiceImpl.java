package com.example.sharemind.consult.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.dto.request.CreateConsultRequest;
import com.example.sharemind.consult.dto.request.GetConsultRequest;
import com.example.sharemind.consult.dto.response.GetConsultResponse;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.global.exception.IncorrectPasswordException;
import com.example.sharemind.consult.mapper.ConsultMapper;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.counselor.exception.CounselorNotFoundException;
import com.example.sharemind.counselor.repository.CounselorRepository;
import com.example.sharemind.customer.domain.Customer;
import com.example.sharemind.customer.mapper.CustomerMapper;
import com.example.sharemind.customer.repository.CustomerRepository;
import com.example.sharemind.message.dto.response.MessageResponse;
import com.example.sharemind.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final CustomerRepository customerRepository;
    private final CounselorRepository counselorRepository;
    private final ConsultRepository consultRepository;
    private final MessageRepository messageRepository;

    private final CustomerMapper customerMapper;
    private final ConsultMapper consultMapper;

    @Override
    @Transactional
    public void createConsult(CreateConsultRequest createConsultRequest) {

        Counselor counselor = counselorRepository.findById(createConsultRequest.getCounselorId())
                .orElseThrow(() -> new CounselorNotFoundException(createConsultRequest.getCounselorId()));

        Customer customer = customerRepository.save(customerMapper.toEntity(createConsultRequest.getEmail()));

        consultRepository.save(consultMapper.toEntity(customer, counselor));
    }

    @Override
    public GetConsultResponse getConsult(UUID consultUuid, GetConsultRequest getConsultRequest) {

        Consult consult = consultRepository.findByConsultUuidAndIsPayAndIsActivated(consultUuid, true, true)
                .orElseThrow(() -> new ConsultNotFoundException(consultUuid));

        boolean loginByCustomer;

        if (consult.getCustomerPassword().equals(getConsultRequest.getPassword())) {
            loginByCustomer = true;
        } else if (consult.getCounselorPassword().equals(getConsultRequest.getPassword())) {
            loginByCustomer = false;
        } else {
            throw new IncorrectPasswordException();
        }

        List<MessageResponse> messageResponses = messageRepository.findAllByConsult(consult)
                .stream()
                .map(MessageResponse::from)
                .toList();

        return GetConsultResponse.from(loginByCustomer, consult, messageResponses);
    }
}
