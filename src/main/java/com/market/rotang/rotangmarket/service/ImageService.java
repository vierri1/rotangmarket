package com.market.rotang.rotangmarket.service;

import com.market.rotang.rotangmarket.exception.ImageNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ImageService {

    private final Path imagesPath;

    public ImageService(@Value("${images.path}") String imagesPath) {
        this.imagesPath = Paths.get(imagesPath);
    }

    public byte[] get(String imageName) {
        Path imagePath = null;
        try {
            imagePath = getImagePath(imageName);
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            log.info("Image not found: {}", imagePath.toString());
            throw new ImageNotFoundException(imageName);
        }
    }

    public boolean add(MultipartFile image) {
        //todo check file type (Files.probeContentType())
        try {
            Files.write(getImagePath(image.getOriginalFilename()), image.getBytes());
            return true;
        } catch (IOException e) {
            log.error("Error upload image: {}", e.getMessage());
            return false;
        }
    }

    public boolean remove(String imageName) {
        try {
            return Files.deleteIfExists(getImagePath(imageName));
        } catch (IOException e) {
            log.error("Error delete image names: {}", e.getMessage());
            return false;
        }
    }

    public List<String> getNames() {
        try (DirectoryStream<Path> imagesStream = getImagesStream()) {
            List<String> imageNames = new ArrayList<>();
            imagesStream.iterator()
                    .forEachRemaining(path -> imageNames.add(path.getFileName().toString()));
            return imageNames;
        } catch (IOException e) {
            log.error("Error get image names: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    private DirectoryStream<Path> getImagesStream() throws IOException {
        return Files.newDirectoryStream(
                imagesPath,
                path -> path.toFile().isFile());
    }

    private Path getImagePath(String imageName) {
        return imagesPath.resolve(Paths.get(imageName));
    }


}
