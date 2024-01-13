package pl.karandysm.redditclone.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karandysm.redditclone.constants.HttpSessionConstants;
import pl.karandysm.redditclone.model.User;
import pl.karandysm.redditclone.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/loggedUser")
    public ResponseEntity<User> loggedUser(HttpSession session) {
        logger.info("Session attribute user is: " + session.getAttribute(HttpSessionConstants.USER));
        Optional<User> user = Optional.ofNullable((User) session.getAttribute(HttpSessionConstants.USER));
//        logger.info("Session user is: " + user.orElse(null));
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
