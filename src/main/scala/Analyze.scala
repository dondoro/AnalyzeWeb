package jp.co.guru.AnalyzeWeb

import java.sql.DriverManager

object Analyze {
  def analyze(query: String) = {
    val user = "vegnavi"
    val pass = "vegnavi"
      
    Class.forName("org.postgresql.Driver").newInstance
    val conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/vegnavi", user, pass)
//    conn.setAutoCommit(false)
    
    val stmt = conn.createStatement();
    val rs = stmt.executeQuery("explain analyze " + query)
    val s = new StringBuilder
    while (rs.next()) {
      s.append(rs.getString(1)).append("\n")
    }
    conn.close
    s.toString
  }
}