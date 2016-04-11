package com.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

@Service
public class GithubLookupAsyncService {
	AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(new HttpComponentsAsyncClientHttpRequestFactory());
	
	public ListenableFuture<ResponseEntity<User>>  findUser(String user) throws InterruptedException {
        System.out.println("Looking up " + user);
        ListenableFuture<ResponseEntity<User>> results = asyncRestTemplate.getForEntity("https://api.github.com/users/" + user, User.class);
        // Artificial delay of 1s for demonstration purposes
        //Thread.sleep(1000L);
        System.out.println("Inside github lookup service: " + Thread.currentThread().getName());
        return results;
    }

}
