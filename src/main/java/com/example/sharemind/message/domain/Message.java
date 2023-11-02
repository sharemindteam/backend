package com.example.sharemind.message.domain;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consult_id")
    private Consult consult;

    private Boolean isCustomer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Message(Consult consult, Boolean isCustomer, String content) {
        this.consult = consult;
        this.isCustomer = isCustomer;
        this.content = content;
    }
}
