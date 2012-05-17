package controllers
import play.api.mvc._
import play.api._
import play.api.data._
import play.api.data.Forms._
import br.com.caelum.gnarus.runner.mysql.Databases
import br.com.caelum.gnarus.runner.mysql.SetResults
import br.com.caelum.gnarus.runner.mysql.UpdateResults
import models.ExerciseDAO
import br.com.caelum.gnarus.runner.mysql.MySQL
import br.com.caelum.gnarus.runner.User
import play.api.cache.Cache
import play.api.Play.current
import play.api.libs.json
import play.api.libs.json.Json

object SQLExecutor extends Controller {

  val SqlTag = ".*\\[sql\\]\\s*(.*)\\s*\\[/sql\\].*".r

  def execute(id: Long) = Action { implicit request =>
    session.get("userId").map { userId =>
	    val currentExercise = ExerciseDAO.load(id)
	    processQuery(request, userId.toLong, currentExercise)      
    }.get

  }

  private def processQuery(request: play.api.mvc.Request[play.api.mvc.AnyContent], userId: Long, currentExercise: models.Exercise): play.api.mvc.Result = {
    request.body.asFormUrlEncoded match {
      case Some(params) => {
        val sql = params.get("sql").get(0)
        val returnUri = params.get("returnUri").get(0)

        val actualSql = sql.trim match {
          case SqlTag(content) => content
          case content => content
        }
        val attempt = Databases.mysql { (db) =>
          val creationResults = db.run(parse(currentExercise.setupQuery))
          val results = db.run(actualSql.toUpperCase, true)
          currentExercise.newAttempt(results, db, User(userId),returnUri)
        }
        Ok(Json.toJson(
        	Map("query" -> attempt.query,"userId" -> attempt.user.id.toString, "correctness" -> (if(attempt.correct) 100 else 0).toString,"description" -> attempt.description.getOrElse(""))
           ))
      }
      case None => BadRequest("nao passou o sql")
    }
  }
  
  //usei isso aqui pq por alguma noobisse minha os \n nao estavam sendo mantidos no banco.
  private def parse(sql:String) = sql.split(";").mkString(";\n")
}
  
