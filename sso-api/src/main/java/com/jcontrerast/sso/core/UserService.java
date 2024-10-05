package com.jcontrerast.sso.core;

import com.jcontrerast.sso.model.User;
import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserService {
    Page<User> getAll(PageFilterDTO filter);

    User getById(UUID id);

    User create(User user);

    User update(User user);

    void delete(UUID id);

    void updatePassword(UUID id, String newPassword);

    void uploadProfilePicture(UUID id, MultipartFile file);
}
