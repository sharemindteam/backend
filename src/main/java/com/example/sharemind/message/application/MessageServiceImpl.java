package com.example.sharemind.message.application;

import com.example.sharemind.consult.domain.Consult;
import com.example.sharemind.consult.exception.ConsultNotFoundException;
import com.example.sharemind.consult.repository.ConsultRepository;
import com.example.sharemind.message.domain.Message;
import com.example.sharemind.message.dto.request.PostNewReq;
import com.example.sharemind.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConsultRepository consultRepository;

    @Override
    @Transactional
    public void saveMessage(PostNewReq postNewReq) {
        Consult consult = consultRepository.findByConsultUuid(postNewReq.getConsultUuid())
                .orElseThrow(ConsultNotFoundException::new);

        Message message = Message.builder()
                .consult(consult)
                .isSender(postNewReq.getIsSender())
                .content(postNewReq.getContent())
                .build();
        messageRepository.save(message);
    }
}



