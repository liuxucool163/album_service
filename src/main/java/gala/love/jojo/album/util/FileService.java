package gala.love.jojo.album.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.logging.Logger;

@Service
public class FileService {
    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());

    public String saveImage(MultipartFile file, int i, Long id) throws IOException {
        String folderPath = "/moments/image/" + id;
        Path folder = Paths.get(folderPath);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        // 获取当前时间戳作为文件名
        String fileName = Instant.now().getEpochSecond() + "_" + i + "." + getFileExtension(file.getOriginalFilename());
        Path filePath = folder.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return folderPath + "/" + fileName;
    }

    private String getFileExtension(String filename) {
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            return filename.substring(i + 1);
        }
        return "";
    }
}