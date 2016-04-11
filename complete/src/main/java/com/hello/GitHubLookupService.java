package com.hello;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubLookupService {

    RestTemplate restTemplate = new RestTemplate();

    @Async
    public Future<User> findUser(String user) throws InterruptedException {
        System.out.println("Inside github lookup service: " + Thread.currentThread().getName());
        return new AsyncResult<User>(restTemplate.getForObject("https://api.github.com/users/" + user, User.class));
    }

}
