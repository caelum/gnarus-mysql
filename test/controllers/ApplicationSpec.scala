package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import models.ExerciseDAO
import models.Exercise
import models.AttemptDAO
import models.Attempt
import models.Attempt
import br.com.caelum.gnarus.runner.User

class ApplicationSpec extends Specification{
  
  
	"Exercise" should {
	  "be showed if id,userId and returnUri were passed" in {
	    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
	      ExerciseDAO.save(Exercise(None,"select * from Compras","select * from Compras",Some(1)))	      
	      val Some(result) = routeAndCall(FakeRequest(GET,"/1?attempt[author_id]=2&attempt[return_uri]=http://online.caelum.com.br"))	      
	      val pageContent = contentAsString(result)
	      pageContent must contain("http://online.caelum.com.br")
	    }
	  }
	  
	  "be showed with the last attempt query" in {
	    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
	      ExerciseDAO.save(Exercise(None,"select * from Compras","select * from Compras",Some(1)))
	      val lastAttempt = Attempt(None,ExerciseDAO.load(1),"select * from Compras",User(1),true,None,"http://online.caelum.com.br")
	      AttemptDAO.save(lastAttempt)
	      
	      val Some(result) = routeAndCall(FakeRequest(GET,"/1?attempt[author_id]=1&attempt[return_uri]=http://online.caelum.com.br"))	      
	      val pageContent = contentAsString(result)
	      
	      pageContent.must(contain(lastAttempt.query))
	    }
	  }	  
	} 
}
