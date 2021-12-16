package bg.softuni.final_project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);

}
