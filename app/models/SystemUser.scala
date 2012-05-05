package models
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
case class SystemUser(optionalId: Option[Long], login: String, pwd:String){
  var id:Long = _
  optionalId.map(this.id = _)
}
case class Password(pwd: String) {
  def hash = pwd
}

object UserDAO {
  
  val userParser = {
    get[Long]("id") ~
      get[String]("login") ~
      get[String]("password") map {
        case id ~ login ~ password => SystemUser(Some(id),login,password)
        case _ => throw new RuntimeException("Deveria ter encontrado!!")
      }

  }  
  
	def findByLogin(login:String):Option[SystemUser] = {
	  DB.withConnection{ implicit c =>
	   SQL("select * from User where login={login} and password={pwd}").on("login" -> login).as(userParser.singleOpt) 
	  }
	}
}