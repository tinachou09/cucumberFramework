name := "cucumberFramework"

version := "0.1"

scalaVersion := "2.13.5"

scalacOptions ++= Seq("unchecked", "deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium" % "selenium-java" % "3.141.59",
  "com.typesafe.play" %% "play-json" % "2.7.4",
  "com.typesafe.play" %% "play-json-joda" % "2.7.4",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "org.scalatest" %% "scalatest" % "3.0.8" excludeAll ExclusionRule(organization = "org.seleniumhq.selenium"),
  "com.typesafe.play" %% "play-test" % "2.8.1" % Test,
  "io.cucumber" % "cucumber-junit" % "6.7.0" % "test",
  "io.cucumber" % "cucumber-scala_2.13" % "6.7.0" % "test",
  "io.cucumber" % "gherkin" % "15.0.2"% "test",
  "io.cucumber" % "cucumber-java" % "6.1.1"% "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2" % "test",
  "ru.yandex.qatools.ashot" % "ashot" % "1.5.4"

)
