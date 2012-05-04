package br.com.caelum.gnarus.runner.mysql

import java.util.concurrent.atomic.AtomicLong
import models.Attempt

object Databases {

  def mysql(f:MySQL => Attempt):Attempt = {
    val db = new MySQL(newName)
    try {
      val attempt = f(db)
      return attempt
    } finally {
      db.close
    }
  }

  private def newName = { "test_db_" + DatabaseControl.inc }

}

object DatabaseControl {
  private val counter = new AtomicLong()
  def inc:Long = counter.incrementAndGet()
}