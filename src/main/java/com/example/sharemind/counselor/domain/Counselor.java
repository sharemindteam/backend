package com.example.sharemind.counselor.domain;

import com.example.sharemind.global.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Counselor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counselorId;

    private String email;

    private String nickname;

    @Builder
    public Counselor(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
