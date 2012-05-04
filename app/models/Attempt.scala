package models
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import br.com.caelum.gnarus.runner.mysql.Results
import br.com.caelum.gnarus.runner.mysql.MySQL
import br.com.caelum.gnarus.runner.mysql.SetResults
import br.com.caelum.gnarus.runner.mysql.UpdateResults
import br.com.caelum.gnarus.runner.User

case class Attempt(optionalId: Option[Long], exercise: Exercise, query: String, user: User, correct: Boolean, description: Option[String], returnUri: String) {

  var id: Long = _
  optionalId.map(this.id = _)
}

object AttemptDAO {

  val lastAttemptParser = {
    get[Long]("attempt.id") ~
      get[Long]("attempt.exercise_id") ~
      get[String]("attempt.query") ~
      get[Long]("attempt.user_id") ~
      get[Boolean]("attempt.correct") ~
      get[Option[String]]("attempt.description") ~
      get[String]("attempt.returnUri") map {
        case id ~ exerciseId ~ query ~ userId ~ correct ~ description ~ returnUri =>
          Attempt(Some(id), new Exercise(exerciseId), query, User(userId), correct, description, returnUri)
        case _ => throw new RuntimeException("Deveria ter encontrado!!")
      }

  }

  def save(attempt: Attempt) {
    DB.withConnection { implicit c =>
      SQL("insert into Attempt(exercise_id,query,user_id,correct,description,returnUri) values({exercise_id},{query},{user_id},{correct},{description},{returnUri})")
        .on("exercise_id" -> attempt.exercise.id, "query" -> attempt.query, "user_id" -> attempt.user.id,
          "correct" -> attempt.correct, "description" -> attempt.description.getOrElse(null), "returnUri" -> attempt.returnUri).
          executeUpdate
    }
  }

  def last(user: User, exercise: Exercise): Option[Attempt] = DB.withConnection { implicit c =>
    SQL("""select att.* from Attempt as att inner join Exercise as ex on att.exercise_id = ex.id 
         where att.user_id={userId} and att.exercise_id={exercise_id}
         order by att.id desc limit 1""")
      .on("userId" -> user.id, "exercise_id" -> exercise.id).as(lastAttemptParser.singleOpt)
  }

}