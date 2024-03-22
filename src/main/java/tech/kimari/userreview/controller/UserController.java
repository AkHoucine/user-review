package tech.kimari.userreview.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.kimari.userreview.entity.User;
import tech.kimari.userreview.service.UserService;

import java.awt.*;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @PostMapping(path = "signin")
    public void signin(@RequestBody User user) {
        log.info("Sign In");
        this.userService.signin(user);

    }
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation) {
        log.info("Activation");
        this.userService.activation(activation);

    }
}
