package jp.co.guru.AnalyzeWeb

import javax.servlet.http.HttpServlet
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http._
import jp.co.guru.PostgreSQLAnalyze.Parse.ResultIterator
import jp.co.guru.PostgreSQLAnalyze.Data

class ParameterlizedTimeLine extends HttpServlet {
  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override protected def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    
    val plan = new ResultIterator(Analyze.analyze(Query.query1)).toList
    response.getWriter.write(HighChartTemplate.page(plan))
  }
}