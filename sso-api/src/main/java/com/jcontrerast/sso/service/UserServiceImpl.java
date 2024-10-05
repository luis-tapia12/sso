package com.jcontrerast.sso.service;

import com.jcontrerast.sso.core.UserService;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.repository.UserRepository;
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
public class UserServiceImpl implements UserService {
    private final String rootDir;
    private final UserRepository repository;
    private final StorageService storageService;

    public UserServiceImpl(
            @Value("${images.base-dir}") String baseDir,
            @Value("${images.profile-pictures.dir}") String profilePicturesDir,
            UserRepository repository,
            StorageService storageService) {
        this.rootDir = SsoUtils.getPath(baseDir, profilePicturesDir);
        this.repository = repository;
        this.storageService = storageService;
    }

    @Override
    public Page<User> getAll(PageFilterDTO filter) {
        Pageable pageable = Utils.getPageable(filter);

        return repository.findAll(pageable);
    }

    @Override
    public User getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The user with id " + id + " does not exist"));
    }

    @Override
    public User create(User user) {
        user.setId(UUID.randomUUID());
        if (!repository.existsById(user.getId())) {
            return repository.save(user);
        } else {
            throw new ResourceAlreadyExistsException("The user with id " + user.getId() + " already exists");
        }
    }

    @Override
    public User update(User user) {
        Assertions.isNotNull(user.getId());
        Optional<User> source = repository.findById(user.getId());

        if (source.isPresent()) {
            Utils.copyNotNull(user, source.get());
            return repository.save(source.get());
        } else {
            throw new ResourceNotFoundException("The user with id " + user.getId() + " does not exist");
        }
    }

    @Override
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("The user with id " + id + " does not exist");
        }
    }

    @Override
    public void updatePassword(UUID id, String newPassword) {
        User user = getById(id);

        user.setPassword(newPassword);
        repository.save(user);
    }

    @SneakyThrows
    @Override
    public void uploadProfilePicture(UUID id, MultipartFile file) {
        User user = getById(id);

        if (user.getProfilePictureUrl() != null) {
            storageService.delete(rootDir, user.getProfilePictureUrl());
        }

        String fileName = id.toString() + "." + SsoUtils.getFileExtension(file);
        storageService.upload(rootDir, fileName, file.getInputStream());
        user.setProfilePictureUrl(fileName);

        repository.save(user);
    }
}
