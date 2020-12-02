name := "Kodumaro Effects"
organization := "info.cacilhas.kodumaro"
version := "1.0.2"
javacOptions ++= Seq("-target", "1.8", "-Xlint")
scalaVersion := "2.13.0"
scalacOptions ++= Seq(
  "-deprecation", // warn on deprecation
  "-feature",     // warn on feature
  "-unchecked",   // warn on unchecked
  "-language:_",  // enable advanced features
)
Test / fork := true
Test / envVars += "TEST" -> "1"
bintrayOrganization := Some("kodumaro")
licenses += ("BSD 3-Clause", url("https://opensource.org/licenses/BSD-3-Clause"))
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % Test

test in assembly := {}
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) =>
    xs map {_.toLowerCase} match {
    case "manifest.mf"  :: Nil |
         "index.list"   :: Nil |
         "dependencies" :: Nil => MergeStrategy.discard
    case _                     => MergeStrategy.last
    }
  case _ => MergeStrategy.first
}

assemblyJarName in assembly := "kodumaro-effects.jar"
