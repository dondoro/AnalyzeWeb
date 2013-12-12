organization := "jp.co.guru"

name := "AnalyzeWeb"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
   "com.typesafe" % "scalalogging-slf4j_2.10" % "1.0.1",
   "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
   "org.eclipse.jetty" % "jetty-webapp" % "8.1.0.v20120127" % "container",
   "org.clapper" % "avsl_2.10" % "1.0.1",
   "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
   "com.typesafe.slick" %% "slick" % "2.0.0-M2",
   "jp.co.guru" % "postgresql-analyze_2.10" % "0.1.0-SNAPSHOT",
   "postgresql" % "postgresql" % "9.1-901.jdbc4" % "runtime"
)

seq(webSettings :_*)

