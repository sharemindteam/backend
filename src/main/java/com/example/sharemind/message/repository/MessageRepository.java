package com.example.sharemind.message.repository;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.message.domain.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByConsult(Consult consult);

    List<Message> findAllByConsult(Consult consult, Sort sort);

    int countByConsultAndIsCustomer(Consult consult, boolean isCustomer);
}
