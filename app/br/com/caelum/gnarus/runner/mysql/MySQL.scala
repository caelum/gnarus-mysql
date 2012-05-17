package br.com.caelum.gnarus.runner.mysql

import java.sql.{SQLException, DriverManager}
import org.slf4j.LoggerFactory
import play.api.db._
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.Play

object MySQL {
  val logger = LoggerFactory.getLogger(classOf[MySQL])
}
class MySQL(val name:String) {
  val url = Play.application().configuration().getString("db.default.url")
  val login = Play.application().configuration().getString("db.default.user")
  val pwd = Play.application().configuration().getString("db.default.password")
  val con = DriverManager.getConnection(url, login, pwd)

  def run(sql:String, filter:Boolean = false):Results = {
    if(filter && sql.contains(";")) {
      new ExceptionResults(new SQLException("Unsupported ';'"))
    }
    sql.split(";\n").foldLeft(new EmptyResults().asInstanceOf[Results]) {
      (r:Results, sql:String) => {
        runThisGuy(sql, filter)
      }
    }
  }

  private def runOnSession(sql:String) {    
    DB.withTransaction {implicit c => SQL(sql).executeUpdate()}    
  }

  def runThisGuy(originalSql:String, filter:Boolean):Results= {
    val parsedSql = asUserTable(originalSql)
    val result:Results = try {
      val check = parsedSql.toLowerCase.trim
      if(filter && check.contains("drop")) {
        MySQL.logger.info("quitting a drop operation '" + parsedSql + "'")
        new ExceptionResults(new SQLException("Unsupported operation 'drop'"))
      } else if(check.startsWith("select")) {
        MySQL.logger.info("ready to execute '" + parsedSql + "'")
        new SetResults(statement.executeQuery(parsedSql))
      } else if(!filter || check.startsWith("insert") || check.startsWith("update") || check.startsWith("delete") || check.startsWith("alter") || check.startsWith("create")) {
        MySQL.logger.info("ready to execute '" + parsedSql + "'")
        new UpdateResults(statement.executeUpdate(parsedSql), new SetResults(statement.executeQuery(asUserTable("select * from COMPRAS"))))
      } else {
        MySQL.logger.info("unsupported operation '" + parsedSql + "'")
        new ExceptionResults(new SQLException("Unsupported operation '" + parsedSql + "'"))
      }
    } catch {
      case ex:SQLException => {
       ex.printStackTrace()
       new ExceptionResults(ex) 
      }
    }    
    result.sql = originalSql
    result
  }
  
  def asUserTable(query:String):String = {
    query.toUpperCase().replace("COMPRAS","COMPRAS_"+name).replace("CONTAS","CONTAS_"+name)    
  }
  
  def statement = {
    val st = con.createStatement()
    st.setQueryTimeout(5)
    st.setMaxRows(40)
    st
  }

  def close() {
    try {
      con.close()
      runOnSession(asUserTable("drop table if exists COMPRAS"))
      runOnSession(asUserTable("drop table if exists CONTAS"))
    } catch {
      case ex:Exception => ex.printStackTrace
    }
  }

}