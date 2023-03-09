package com.planification.wf.controlleur;

import com.planification.wf.entity.Message;
import com.planification.wf.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class WebSocketChatController {

    private MessageService messageService;

    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public Message chat(Message message) {
        message.setDate(LocalDateTime.now());
        this.messageService.saveMessage(message);
        return message;
    }

}