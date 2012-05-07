package br.com.caelum.gnarus.runner.mysql

import java.sql.{SQLException, ResultSet}
import collection.mutable.ListBuffer
import scala.collection.JavaConversions._
import java.util.{ArrayList, List}

case class ColumnAndValue(name:String,value:String)

class SetResults(val rs:ResultSet) extends Results {
  
  private type Record = List[ColumnAndValue]
  
  private val columns = rs.getMetaData.getColumnCount

  val lines:List[Record] = {
    val list = ListBuffer[Record]()
    while(rs.next()) {
      val line = for( i <- 1 to columns) yield ColumnAndValue(rs.getMetaData().getColumnName(i),rs.getString(i))
      list += new ArrayList(line.sortBy(_.name))
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