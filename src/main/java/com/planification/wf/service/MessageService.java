package com.planification.wf.service;

import com.planification.wf.models.entity.Message;
import com.planification.wf.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private static final int DEFAULT_SIZE = 10;

    private final MessageRepository messageRepository;

    public void saveMessage(Message message){

                this.messageRepository.save(message);
    }
    public List<Message> getMessages(){
       List<Message> listOfMessages = messageRepository.findByOrderByDateDesc(Pageable.ofSize(DEFAULT_SIZE));
        Collections.reverse(listOfMessages);
        return listOfMessages;

    }



}
