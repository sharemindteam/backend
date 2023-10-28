package com.example.sharemind.consult.domain;

import com.example.sharemind.counselor.domain.Counselor;
import com.example.sharemind.customer.domain.Customer;
import com.example.sharemind.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Consult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_id")
    private Long consultId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    private UUID consultUuid;

    private String password;

    private Boolean isPay;

    private Boolean isRefund;

    private Boolean isReply;

    @Builder
    public Consult(Customer customer, Counselor counselor, UUID consultUuid, String password, Boolean isPay, Boolean isRefund, Boolean isReply) {
        this.customer = customer;
        this.counselor = counselor;
        this.consultUuid = consultUuid;
        this.password = password;
        this.isPay = isPay;
        this.isRefund = isRefund;
        this.isReply = isReply;
    }
}
