package models
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
case class SystemUser(login: String, password:String){
  def this(id:Long,login:String,password:String) = {
    this(login,password)
    this.id = id
  }
  var id:Long = _
}
case class Password(pwd: String) {
  def hash = pwd
}

object UserDAO {
  
  val userParser = {
    get[Long]("id") ~
      get[String]("login") ~
      get[String]("password") map {
        case id ~ login ~ password => new SystemUser(id,login,password)
        case _ => throw new RuntimeException("Deveria ter encontrado!!")
      }

  }  
  
	def findByLogin(login:String):Option[SystemUser] = {
	  DB.withConnection{ implicit c =>
	   SQL("select * from SystemUser where login={login}").on("login" -> login).as(userParser.singleOpt) 
	  }
	}
}