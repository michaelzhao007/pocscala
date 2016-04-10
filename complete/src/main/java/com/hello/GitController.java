package com.hello;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GitController {
  
  @Autowired
  private GitHubLookupService service;
  
  @RequestMapping("/user1")
  @ResponseBody
  public Future<User> getUser() throws InterruptedException {
      System.out.println("Inside controller: " + Thread.currentThread().getName());
      Future<User> res = service.findUser("PivotalSoftware");
      System.out.println("after res");
      return res;
      
  }
}


/*
 * Copyright 2016 Capital One Financial Corporation All Rights Reserved.
 * 
 * This software contains valuable trade secrets and proprietary information of
 * Capital One and is protected by law. It may not be copied or distributed in
 * any form or medium, disclosed to third parties, reverse engineered or used in
 * any manner without prior written authorization from Capital One.
 */
