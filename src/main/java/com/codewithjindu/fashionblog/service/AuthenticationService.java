package com.codewithjindu.fashionblog.service;

import com.codewithjindu.fashionblog.dto.LoginPayload;
import com.codewithjindu.fashionblog.dto.RegistrationPayload;
import com.codewithjindu.fashionblog.exception.BadCredentialsException;

public interface AuthenticationService {
    String register(RegistrationPayload registrationPayload) throws BadCredentialsException;
    String login(LoginPayload loginPayload) throws BadCredentialsException;
    String logout();
}
