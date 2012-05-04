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
  private val who = "`%s`@`localhost`".format(name)

  runOnSession("drop database if exists " + name)
  runOnSession("create database " + name)
  try {
    runOnSession("create user %s identified by '%s'".format(who, name))
    runOnSession("grant create,delete,index,insert,select,update,alter on `%s`.* to %s".format(name, who))
  } catch {
    case e => MySQL.logger.warn("user " + name + " já existe")
  }

  val url = Play.application().configuration().getString("db.default.base_url")
  val con = DriverManager.getConnection(url + name, name, name)

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

  def runThisGuy(sql:String, filter:Boolean):Results= {
    val result:Results = try {
      val check = sql.toLowerCase.trim
      if(filter && check.contains("drop")) {
        MySQL.logger.info("quitting a drop operation '" + sql + "'")
        new ExceptionResults(new SQLException("Unsupported operation 'drop'"))
      } else if(check.startsWith("select")) {
        MySQL.logger.info("ready to execute '" + sql + "'")
        new SetResults(statement.executeQuery(sql))
      } else if(!filter || check.startsWith("insert") || check.startsWith("update") || check.startsWith("delete") || check.startsWith("alter") || check.startsWith("create")) {
        MySQL.logger.info("ready to execute '" + sql + "'")
        new UpdateResults(statement.executeUpdate(sql), new SetResults(statement.executeQuery("select * from COMPRAS")))
      } else {
        MySQL.logger.info("unsupported operation '" + sql + "'")
        new ExceptionResults(new SQLException("Unsupported operation '" + sql + "'"))
      }
    } catch {
      case ex:SQLException => {
       new ExceptionResults(ex) 
      }
    }    
    result.sql = sql
    result
  }
  def statement = {
    val st = con.createStatement()
    st.setQueryTimeout(5)
    st.setMaxRows(20)
    st
  }

  def close() {
    try {
      con.close()
      runOnSession("drop database if exists " + name)
      runOnSession("drop user " + who)
    } catch {
      case ex:Exception => ex.printStackTrace
    }
  }

}