package br.com.caelum.gnarus.runner.mysql

import java.sql.{SQLException, ResultSet}
import collection.mutable.ListBuffer
import scala.collection.JavaConversions._
import java.util.{ArrayList, List}

class SetResults(val rs:ResultSet) extends Results {

  private val columns = rs.getMetaData.getColumnCount

  val lines:List[List[String]] = {
    val list = ListBuffer[List[String]]()
    while(rs.next()) {
      val line = for( i <- 1 to columns) yield rs.getString(i)
      list += new ArrayList(line)
    }
    new ArrayList(list)
  }

  private val columnNames:List[String] = {
    val names = for (i <- 1 to columns) yield rs.getMetaData.getColumnName(i)
    new ArrayList(names)
  }

  def spit = {    
    "there were some results"
  }
  
  def count = lines.size
  
  override def equals(obj:Any):Boolean = {
    val other = obj.asInstanceOf[SetResults]
    return other.lines == this.lines
  }

}
sealed trait Results {
  var sql:String = _
}
class ExceptionResults(val exception:SQLException) extends Results {
}
class UpdateResults(val quantity:Long, val updated:SetResults) extends Results {
}
class EmptyResults extends Results {
}