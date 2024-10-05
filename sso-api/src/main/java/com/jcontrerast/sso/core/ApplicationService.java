package com.jcontrerast.sso.core;

import com.jcontrerast.sso.model.Application;
import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ApplicationService {
    Page<Application> getAll(PageFilterDTO filter);

    Application getById(UUID id);

    Application create(Application application);

    Application update(Application application);

    void delete(UUID id);

    void updateClientSecret(UUID id, String clientSecret);

    void uploadLogo(UUID id, MultipartFile file);
}
