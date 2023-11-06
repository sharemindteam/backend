package com.example.sharemind.consult.mapper;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.customer.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class ConsultMapper {

    public Consult toEntity(Customer customer, Counselor counselor) {
        return Consult.builder()
                .customer(customer)
                .counselor(counselor)
                .consultUuid(UUID.randomUUID())
                .customerPassword(new Random().nextLong(1000000000L, 10000000000L))
                .counselorPassword(new Random().nextLong(1000000000L, 10000000000L))
                .isPay(false)
                .isRefund(false)
                .isActivated(true)
                .build();
    }
}
