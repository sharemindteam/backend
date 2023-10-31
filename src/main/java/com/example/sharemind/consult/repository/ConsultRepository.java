package com.example.sharemind.consult.repository;

import com.example.sharemind.consult.domain.Consult;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Optional<Consult> findByConsultUuid(UUID consultUuid);
}