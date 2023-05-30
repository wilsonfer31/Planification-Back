package com.planification.wf.controlleur.priv;




import com.planification.wf.models.DTO.UserDTO;
import com.planification.wf.service.ImageService;
import com.planification.wf.service.UserService;
import com.planification.wf.models.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final ImageService imageService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("image/{userId}")
    public ResponseEntity<String> addImageToUser(@RequestParam  MultipartFile file, @PathVariable Long userId) throws IOException {
        return ResponseEntity.ok( this.imageService.uploadImageToUser(file, userId));
    }

    @GetMapping("userImage")
    public ResponseEntity<Resource> getImageUser() {
            try {
                Resource imageResource = this.imageService.getImageFile();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);

                return ResponseEntity.ok()
                    .headers(headers)
                    .body(imageResource);
            } catch (RuntimeException e) {
                throw new RuntimeException("Cant get the image");
            }
    }
}
