package com.planification.wf.controlleur;




import com.planification.wf.service.UserService;
import com.planification.wf.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
