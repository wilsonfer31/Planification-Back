package com.planification.wf.controlleur.priv;

import com.planification.wf.models.entity.Message;
import com.planification.wf.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("messages")
public class MessageController {
    private MessageService messageService;

    @GetMapping("/chat")
    private ResponseEntity<List<Message>>  getAllLastMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }

}
