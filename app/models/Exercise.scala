package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import br.com.caelum.gnarus.runner.mysql.{Results,MySQL}
import br.com.caelum.gnarus.runner.mysql.{UpdateResults,SetResults}
import br.com.caelum.gnarus.runner.User
import br.com.caelum.gnarus.runner.mysql.ExceptionResults
import br.com.caelum.gnarus.runner.mysql.EmptyResults

case class Exercise(optionalId:Option[Long],val content:String,val queryValidator:String,expectedCount:Option[Int]){
  
  def this(id:Long) = {
    this(Some(id),null,null,None)
  }
  
  var id:Long = _
  optionalId.map(id => this.id = id)
  
  def newAttempt(attemptedResult:Results,db: MySQL,user:User,returnUri:String): Attempt = {
    val expectedResults = db.run(this.queryValidator).asInstanceOf[SetResults]
    
    val (correct,description) = attemptedResult match {
      case current: UpdateResults => {       
       val correct = this.expectedCount.get == expectedResults.count
       (correct,if(correct) None else Some("O retorno da sua consulta retorno um número de registros diferente do que esperavamos."))
      }
      
      case returnOfSelect: SetResults => {
       val correct = expectedResults == returnOfSelect
       (correct,if(!correct) Some("O retorno não foi o que estavamos esperando. Tente verificar o select para verificar se não esqueceu de algum detalhe") else None)
      }
      
      case error:ExceptionResults => (false,Some("Aconteceu alguma errada na execução de sua query. Cheque a consulta para verificar"))
      
      case empty:EmptyResults => (false,Some("A query não retornou nenhum resultado. Tem certeza que especificou corretamente as condições?"))
      
    }    
    
    val attempt = Attempt(None,this,attemptedResult.sql,user,correct,description,returnUri)
    AttemptDAO.save(attempt)
    return attempt
  }  
  
  
}

object ExerciseDAO {
  val exerciseParser = {
    get[Long]("id") ~
      get[String]("content") ~
      get[String]("queryValidator") ~
      get[Option[Int]]("expectedResult") map {
        case id ~ content ~ queryValidator ~ expectedCount => Exercise(Some(id),content,queryValidator,expectedCount)
        case _ => throw new RuntimeException("oops!!")
      }
  }  
  def save(exercise:Exercise):Unit = {
	  DB.withConnection { implicit c =>
	    SQL("insert into Exercise(content,queryValidator,expectedResult) values({content},{queryValidator},{expectedResult})")
	      .on("content" -> exercise.content, "queryValidator" -> exercise.queryValidator, "expectedResult" -> exercise.expectedCount).executeUpdate
	  }
  }
  
  def update(exercise:Exercise):Unit = {
	  DB.withConnection { implicit c =>
	    SQL("update Exercise set content={content},queryValidator={queryValidator},expectedResult = {expectedResult} where id={id}")
	      .on("id" -> exercise.id,"content" -> exercise.content, "queryValidator" -> exercise.queryValidator, "expectedResult" -> exercise.expectedCount).executeUpdate
	  }
  }  
  
  def load(id:Long):Exercise = {
    DB.withConnection { implicit c =>
      SQL("select * from Exercise where id = {id}").on("id" -> id).as(exerciseParser.single)
    }
  }
  
  def all:List[Exercise] = {
    DB.withConnection { implicit c =>
      SQL("select * from Exercise order by id asc").as(exerciseParser.*)
      
    }
  }
  
}
