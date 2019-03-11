name := "patent_client_scala"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "com.lihaoyi" %% "requests" % "0.1.7"
libraryDependencies += "com.lihaoyi" %% "upickle" % "0.7.1"

logBuffered in Test := false