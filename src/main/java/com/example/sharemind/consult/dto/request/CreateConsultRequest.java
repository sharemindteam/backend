package com.example.sharemind.consult.dto.request;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.customer.domain.Customer;
import lombok.Getter;

import java.util.Random;
import java.util.UUID;

@Getter
public class CreateConsultRequest {

    private Long counselorId;
    private String email;
    private String name;
    private String phoneNumber;
    private String accountNumber;
    private String password;

    public Customer toCustomer() {
        return Customer.builder()
                .nickname("사용자" + new Random().nextInt(9999))
                .email(email)
                .name(name)
                .phoneNumber(phoneNumber)
                .accountNumber(accountNumber)
                .build();
    }

    public Consult toConsult(Customer customer, Counselor counselor) {
        return Consult.builder()
                .customer(customer)
                .counselor(counselor)
                .consultUuid(UUID.randomUUID())
                .password(password)
                .isPay(false)
                .isRefund(false)
                .isReply(false)
                .build();
    }
}
