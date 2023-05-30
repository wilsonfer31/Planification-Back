package com.planification.wf.service;

import com.google.common.io.Files;
import com.planification.wf.models.entity.User;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class ImageService {

    @Value("${spring.image.url}")
    private String imagePath;

    public final UserService userService;


    public ImageService(UserService userService) {
        this.userService = userService;

    }

    public String uploadImageToUser(MultipartFile file, Long userId) throws IOException {
        try {
            User user = this.userService.getById(userId);
            Path storagePath = Paths.get(imagePath).toAbsolutePath().normalize();
            String fileName = user.getId() + "." + Files.getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
            String filePath = storagePath + File.separator + fileName;

            File dest = new File(filePath);
            file.transferTo(dest);
            user.setImagePath(filePath);
            this.userService.updateUser(user);

            return "The file has been uploaded !";
        } catch (IOException e) {
            throw new IOException("Failed to upload the file.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while uploading the file.");
        }

    }
    public Resource getImageFile() {
        try {
            User user = this.userService.getById(AuthenticationGetter.getCurrentUser().getId());
            Path filePath = Path.of(user.getImagePath());
            Resource resource = new UrlResource(filePath.toUri());

            // Check if the file exists and is readable
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
