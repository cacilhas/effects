addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

name := "Kodumaro Effects"
organization := "info.cacilhas.kodumaro"
version := "1.0.0"
javacOptions ++= Seq("-target", "1.8", "-Xlint")
scalaVersion := "2.12.12"
scalacOptions ++= Seq(
  "-deprecation", // warn on deprecation
  "-feature",     // warn on feature
  "-unchecked",   // warn on unchecked
  "-language:_",  // enable advanced features
)
Test / fork := true
Test / envVars ++= Map(
  "TEST" → "1",
)
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.10.0" % Test,
)

test in assembly := {}
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) ⇒
    xs map {_.toLowerCase} match {
    case "manifest.mf" :: Nil  |
         "index.list" :: Nil   |
         "dependencies" :: Nil ⇒ MergeStrategy.discard
    case _ ⇒ MergeStrategy.last
    }
  case _ ⇒ MergeStrategy.first
}

assemblyJarName in assembly := "kodumaro-effects.jar"
