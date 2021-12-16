package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {


    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());

        multipartFile.transferTo(tempFile);


        @SuppressWarnings("unchecked")
        Map<String, String> uploadResult = cloudinary
                .uploader()
                .upload(tempFile, Map.of());


        String url = uploadResult.getOrDefault(URL,
                "https://i.pinimg.com/originals/c5/21/64/c52164749f7460c1ededf8992cd9a6ec.jpg");

        return url;
    }

    @Override
    public boolean delete(String publicId) {
        return false;
    }
}
