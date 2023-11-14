package com.example.sharemind.consult.repository;

import com.example.sharemind.consult.domain.Consult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Optional<Consult> findByConsultId(Long consultId);

    Optional<Consult> findByConsultUuidAndIsPayAndIsActivated(UUID consultUuid, Boolean isPay, Boolean isActivated);

    Optional<Consult> findByConsultIdAndIsPayAndIsActivated(Long consultId, Boolean isPay, Boolean isActivated);

    List<Consult> findAllByIsPayAndIsActivated(Boolean isPay, Boolean isActivated);
}
