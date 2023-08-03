package com.github.gustavoflor.wirepock.entrypoint.web;

import com.github.gustavoflor.wirepock.core.GithubClient;
import com.github.gustavoflor.wirepock.core.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GithubClient githubClient;

    public UserController(final GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return githubClient.findUserByUsername(username);
    }

}
