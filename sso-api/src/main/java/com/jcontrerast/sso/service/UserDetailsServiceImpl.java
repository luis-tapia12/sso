package com.jcontrerast.sso.service;

import com.jcontrerast.sso.dto.UserDTO;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.repository.UserRepository;
import com.jcontrerast.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UUID applicationId;
    private final UserRepository repository;

    public UserDetailsServiceImpl(
            @Value("${application-id}") UUID applicationId,
            UserRepository repository) {
        this.applicationId = applicationId;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = repository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("The user " + userName + " does not exist"));
        List<String> permissions = repository.findAllPermissions(user.getId(), applicationId);
        System.out.println(permissions);
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getPassword(),
                user.getProfilePictureUrl(),
                user.getLanguage(),
                permissions
        );
    }
}
