package com.codewithjindu.fashionblog.controller;

import com.codewithjindu.fashionblog.dto.LoginPayload;
import com.codewithjindu.fashionblog.dto.PostRequestPayload;
import com.codewithjindu.fashionblog.dto.RegistrationPayload;
import com.codewithjindu.fashionblog.exception.BadCredentialsException;
import com.codewithjindu.fashionblog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationPayload registrationPayload){
       var response = authenticationService.register(registrationPayload);
       return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginPayload loginPayload) throws BadCredentialsException {
        var response = authenticationService.login(loginPayload);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        var response = authenticationService.logout();
        return ResponseEntity.ok().body(response);
    }
}
