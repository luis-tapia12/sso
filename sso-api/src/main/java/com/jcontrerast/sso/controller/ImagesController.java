package com.jcontrerast.sso.controller;

import com.jcontrerast.sso.utils.SsoUtils;
import com.jcontrerast.utils.core.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImagesController {
    private final String baseDir;
    private final StorageService storageService;

    public ImagesController(
            @Value("${images.base-dir}") String baseDir,
            StorageService storageService) {
        this.baseDir = baseDir;
        this.storageService = storageService;
    }

    @GetMapping("/{parentDir}/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String parentDir, @PathVariable String fileName) {
        String rootDir = SsoUtils.getPath(baseDir, parentDir);
        Resource resource = storageService.download(rootDir, fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
