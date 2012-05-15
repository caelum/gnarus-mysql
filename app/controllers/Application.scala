package controllers

import play.api._
import play.api.mvc._
import models.ExerciseDAO
import play.api.cache.Cache
import play.api.Play.current
import br.com.caelum.gnarus.runner.User
import models.Attempt
import models.AttemptDAO

object Application extends Controller {
  
  
  def index(id:Long) = Action { implicit request =>
    val userId = request.queryString("attempt[author_id]")(0).toLong
    val returnUri = request.queryString("attempt[return_uri]")(0)
    val exercise = ExerciseDAO.load(id)   
    Ok(views.html.index(exercise,AttemptDAO.last(User(userId),exercise),returnUri)).withSession(session + ("userId" -> userId.toString))
  }
  
}