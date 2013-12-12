package jp.co.guru.AnalyzeWeb

import javax.servlet.http.HttpServlet
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import java.io.IOException
import javax.servlet.ServletException

class ScalaServlet extends HttpServlet {
 @throws(classOf[IOException])
  @throws(classOf[ServletException])
  override protected def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val responseXml =
      <html>
        <head>
          <meta charset="UTF-8"/>
          <title>Scala Servlet</title>
        </head>
        <body>
          <h1>Scala Servlet</h1>
        </body>
      </html>
    response.getWriter.write(responseXml.toString)
  }
}

