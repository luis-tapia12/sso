package com.jcontrerast.sso.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SsoUtils {
    public static String getFileExtension(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name != null && name.contains(".")) {
            return name.substring(name.lastIndexOf('.') + 1);
        }
        return "";
    }

    public static String getPath(String baseDir, String fileName) {
        Path fileStorageLocation = Paths.get(baseDir);
        Path filePath = fileStorageLocation.resolve(fileName).normalize();

        return filePath.toAbsolutePath().toString();
    }
}
