package com.example.sharemind.customer.mapper;

import com.example.sharemind.customer.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomerMapper {

    public Customer toEntity(String email) {
        return Customer.builder()
                .nickname("사용자" + new Random().nextInt(9999))
                .email(email)
                .build();
    }
}
