import Dependencies._
import java.io.File

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "info.pandanote"
ThisBuild / organizationName := "bimmap"

logBuffered in Test := false

lazy val root = (project in file("."))
  .settings(
    name := "BiMMap",
    libraryDependencies += scalaTest % Test
  )

def ls(dir:String) : Seq[File] = {
  new File(dir).listFiles.flatMap {
    case f if f.isDirectory => ls(f.getPath)
    case x => List(x)
  }
}

cleanFiles ++= ls(".").filter(_.getPath.endsWith("~"))

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-collection-contrib" % "0.2.2",
  "org.slf4j" % "slf4j-log4j12" % "1.7.30",
  "org.scalatest" %% "scalatest" % "3.2.11" % Test
)

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}

scalacOptions += "-deprecation"
