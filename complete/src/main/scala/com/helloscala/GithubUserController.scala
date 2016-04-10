

package com.helloscala

import play.api._
import play.api.mvc._
import org.springframework.stereotype.Controller
import org.springframework.core.io.Resource
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import com.hello.GitHubLookupServiceSync
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod}
import org.springframework.web.bind.annotation.ResponseBody
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.springframework.web.bind.annotation.CrossOrigin
import play.api.mvc.Action
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.mvc.Results.Status
import play.mvc.Http.Response
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.hello.User
import com.hello.GitHubLookupService


/*
 * @author hnq583
 */
@Controller
class GithubUserController {
  
    @Autowired
    val gitHubLookupService: GitHubLookupService = null
    
    //def getInstance() = this
    
    @CrossOrigin(origins = Array("http://localhost:9000"))
    @RequestMapping(value = Array("/user"), method = Array(RequestMethod.GET))
    @ResponseBody
    def getUserScala() =   {
       println("in getuserscala: "+ Thread.currentThread().getName)
       val users =   gitHubLookupService.findUser("PivotalSoftware")
       if(users.isDone()) {
       if(users.get() != null) {
         new ResponseEntity(users.get(), HttpStatus.OK)
       }
       else {
         new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
       }
       }
     }
    
  
}