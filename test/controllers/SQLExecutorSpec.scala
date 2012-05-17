package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import models.ExerciseDAO
import models.Exercise
import models.Attempt
import br.com.caelum.gnarus.runner.User
import models.AttemptDAO
import play.api.mvc.Session
import play.api.mvc.Cookies
import play.api.libs.json.Json
import play.api.libs.json.JsValue

class SQLExecutorSpec extends Specification {

  private def executeSqlRequest(sql:String) = {
    val sessionCookie = Session.encodeAsCookie(Session(Map("userId" -> "1")))
    val req = FakeRequest(POST, "/execute/sql/1").
    		 withHeaders(play.api.http.HeaderNames.COOKIE -> Cookies.encode(Seq(sessionCookie))).
    		 withFormUrlEncodedBody("sql" -> sql, "returnUri" -> "http://www.online.caelum.com.br")
    req
  }

  "Running an update query" should {
    "return correctness 100 for correct answer" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        ExerciseDAO.save(Exercise(None, "select * from Compras", "select * from Compras", Some(1)))
        val lastAttempt = Attempt(None, ExerciseDAO.load(1), "select * from Compras", User(1), true, None, "http://online.caelum.com.br")
        AttemptDAO.save(lastAttempt)
        
        val Some(result) = routeAndCall(executeSqlRequest("select * from Compras"))
        val content = contentAsString(result)
        val json = Json.parse(content)
        json \ "correctness" must beEqualTo(Json.toJson("100"))
      }
    }
    
    "return correctness 0 for incorrect answer" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        ExerciseDAO.save(Exercise(None, "select * from Compras", "select * from Compras", Some(1)))
        val lastAttempt = Attempt(None, ExerciseDAO.load(1), "select * from Compras", User(1), true, None, "http://online.caelum.com.br")
        AttemptDAO.save(lastAttempt)
        
        val Some(result) = routeAndCall(executeSqlRequest("select * from Compras order by data desc"))
        val content = contentAsString(result)
        val json = Json.parse(content)
        json \ "correctness" must beEqualTo(Json.toJson("0"))
      }
    }    
  }  
}