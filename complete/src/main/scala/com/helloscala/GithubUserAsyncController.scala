
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
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.hello.GithubLookupAsyncService

@Controller
class GithubUserAsyncController @Autowired() (githubLookupService: GithubLookupAsyncService) {
  
  implicit def toScalaFuture[T] (f: ListenableFuture[T]): Future[T] = {
    val p = promise[T]()
   f.addCallback(new ListenableFutureCallback[T] {
       def onSuccess(result: T) {p.success(result)}
       def onFailure(t: Throwable) {p.failure(t)}
   })
   p.future
  }
  
   @CrossOrigin(origins = Array("http://localhost:9000"))
    @RequestMapping(value = Array("/user2"), method = Array(RequestMethod.GET))
    @ResponseBody
    def getUserScala() =   {
       val result = new DeferredResult[User]
       println("in getuserscala: "+ Thread.currentThread().getName)
       val users =  toScalaFuture(githubLookupService.findUser("PivotalSoftware")).map { _.getBody }
       users onSuccess {
         case user => result.setResult(user)
       }
       users onFailure {
         case t => result.setErrorResult(t)
       }
       result
     }
  
 
  
}