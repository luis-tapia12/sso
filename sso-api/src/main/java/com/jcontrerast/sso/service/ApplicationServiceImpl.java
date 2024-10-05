package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.ApplicationService;
import com.jcontrerast.sso.model.Application;
import com.jcontrerast.sso.repository.ApplicationRepository;
import com.jcontrerast.sso.utils.SsoUtils;
import com.jcontrerast.utils.Assertions;
import com.jcontrerast.utils.Utils;
import com.jcontrerast.utils.core.StorageService;
import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.exception.ResourceAlreadyExistsException;
import com.jcontrerast.utils.exception.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final String rootDir;
    private final ApplicationRepository repository;
    private final StorageService storageService;

    public ApplicationServiceImpl(
            @Value("${images.base-dir}") String baseDir,
            @Value("${images.logos.dir}") String logosDir,
            ApplicationRepository repository,
            StorageService storageService) {
        this.rootDir = SsoUtils.getPath(baseDir, logosDir);
        this.repository = repository;
        this.storageService = storageService;
    }

    @Override
    public Page<Application> getAll(PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findAll(pageable);
    }

    @Override
    public Application getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The application with id " + id + " does not exist"));
    }

    @Override
    public Application create(Application application) {
        application.setId(UUID.randomUUID());
        if (!repository.existsById(application.getId())) {
            return repository.save(application);
        } else {
            throw new ResourceAlreadyExistsException("The application with id " + application.getId() + " already exists");
        }
    }

    @Override
    public Application update(Application application) {
        Assertions.isNotNull(application.getId());
        Optional<Application> source = repository.findById(application.getId());

        if (source.isPresent()) {
            Utils.copyNotNull(application, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The application with id " + application.getId() + " does not exist");
        }
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The application with id " + id + " does not exist");
        }
    }

    @Override
    public void updateClientSecret(UUID id, String clientSecret) {
        Application application = getById(id);

        application.setClientSecret(clientSecret);
        repository.save(application);
    }

    @SneakyThrows
    @Override
    public void uploadLogo(UUID id, MultipartFile file) {
        Application application = getById(id);

        if (application.getLogoUrl() != null) {
            storageService.delete(rootDir, application.getLogoUrl());
        }

        String fileName = id.toString() + "." + SsoUtils.getFileExtension(file);
        storageService.upload(rootDir, fileName, file.getInputStream());
        application.setLogoUrl(fileName);

        repository.save(application);
    }
}
