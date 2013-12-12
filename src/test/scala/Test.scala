package jp.co.guru.AnalyzeWeb

import com.typesafe.scalalogging.slf4j.Logging
import org.scalatest.FunSuite
import jp.co.guru.PostgreSQLAnalyze.Parse.ResultIterator
import jp.co.guru.PostgreSQLAnalyze.Data
import java.sql._
import javax.sql._

class StringFunSuite extends FunSuite with Logging {

  test("first test") {
    val plan = new ResultIterator(Data.orgSecond).toList
//    plan.foreach(p => println(p.excel(plan(0)).mkString("\t")))
  }

  private def getConnection() = {
    val user = "vegnavi"
    val pass = "vegnavi"
      
    Class.forName("org.postgresql.Driver").newInstance
    DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/vegnavi", user, pass)
  }
  
  test("DB test1") {
//    conn.setAutoCommit(false)
    val conn = getConnection
    val stmt = conn.createStatement();
    val rs = stmt.executeQuery("explain analyze " + Query.query1);
    val s = new StringBuilder
    while (rs.next()) {
      s.append(rs.getString(1)).append("\n")
    }
    conn.close
    println(s)
  }
  
    test("DB test2") {
    val conn = getConnection
    val stmt = conn.createStatement();
    val rs = stmt.executeQuery("explain analyze " + Query.query1);
    val s = new StringBuilder
    while (rs.next()) {
      s.append(rs.getString(1)).append("\n")
    }
    conn.close
    val plan = new ResultIterator(s.toString).toList
    println(plan.mkString("\n"))
  }

}
