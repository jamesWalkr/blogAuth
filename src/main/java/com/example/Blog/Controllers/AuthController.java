package com.example.Blog.Controllers;

import com.example.Blog.DTOS.LoginRequest;
import com.example.Blog.DTOS.RegisterRequest;
import com.example.Blog.Models.User;
import com.example.Blog.Service.TokenService;
import com.example.Blog.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private UserService userService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody RegisterRequest registerRequest){
        logger.debug("request {} " + registerRequest.toString());
        User u = userService.signup(registerRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        return tokenService.generateToken(authentication);
    }

}
