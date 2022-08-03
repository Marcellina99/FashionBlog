package com.codewithjindu.fashionblog.service.impl;
import com.codewithjindu.fashionblog.constant.DefaultMessage;
import com.codewithjindu.fashionblog.constant.Role;
import com.codewithjindu.fashionblog.dto.LoginPayload;
import com.codewithjindu.fashionblog.dto.RegistrationPayload;
import com.codewithjindu.fashionblog.exception.BadCredentialsException;
import com.codewithjindu.fashionblog.model.User;
import com.codewithjindu.fashionblog.repository.UserRepository;
import com.codewithjindu.fashionblog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;


    @Override
    public String register(RegistrationPayload registrationPayload) throws BadCredentialsException {
        String email = registrationPayload.getEmail();
        String username = registrationPayload.getUsername();
        String password = registrationPayload.getPassword();

        if(userRepository.existsByEmail(email)){
            throw new BadCredentialsException(DefaultMessage.DUPLICATE_CREDENTIAL_ERROR);
        }

        User user = User.builder()
                .email(email)
                .username(username)
                .password(password)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);

        return DefaultMessage.SUCCESSFUL_REGISTRATION;
    }

    @Override
    public String login(LoginPayload loginPayload) throws BadCredentialsException {
        User user = userRepository
                .findUserByEmailOrUsername(loginPayload.getIdentifier())
                .orElseThrow(
                        () -> new BadCredentialsException(DefaultMessage.INCORRECT_LOGIN_CREDENTIALS));

        if (!user.getPassword().equals(loginPayload.getPassword())){
            throw new BadCredentialsException(DefaultMessage.INCORRECT_LOGIN_CREDENTIALS);
        }

        httpSession.setAttribute("Login_ID", user.getId());
        httpSession.setAttribute("Role",  user.getRole());

        return DefaultMessage.SUCCESSFUL_LOGIN;

    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return DefaultMessage.SUCCESSFUL_LOGOUT;
    }
}
