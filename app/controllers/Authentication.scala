package controllers
import play.api.mvc.Controller
import play.api.data.Forms._
import play.api.data._
import models.UserDAO
import play.api.libs._
import play.api.mvc._
import models.SystemUser
import models.SystemUser

object Authentication extends Controller{

  val loginForm = Form(
    mapping(
      "login" -> text,
      "password" -> text)
      (SystemUser.apply)
      (SystemUser.unapply)
      verifying ("Invalid email or password", result => result match {
        case systemUser => check(systemUser.login, systemUser.password)
      }))

  private def check(email: String, password: String): Boolean = {
    val possibleUser = UserDAO.findByLogin(email)
    val hashedPassword = Crypto.sign(password)
    possibleUser.map(_.password == hashedPassword).getOrElse(false)
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => Ok(views.html.login(formWithErrors)),
      user => Redirect(routes.Exercises.list()).withSession(Security.username -> user.login))
  }

    def logout = Action {
      Redirect(routes.Authentication.login).withNewSession.flashing(
        "success" -> "You are now logged out.")
    }

}

trait Secured {
  
  type UserName = String
  
  type TheAction = Request[AnyContent] => Result    
   
  def username(request: RequestHeader):Option[UserName] = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Authentication.login)

  def withAuth(f: => TheAction) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(request))
    }
  }

}