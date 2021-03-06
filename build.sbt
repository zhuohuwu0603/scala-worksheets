name := "testjes"

version := "1.0"

scalaVersion := "2.11.11"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

val circeVersion = "0.8.0"
val sparkVersion = "2.3.0"
val slf4jVersion = "1.7.16"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.15",
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-graphx" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.typelevel" %% "cats-core" % "1.0.0-MF",
  "org.scalacheck" %% "scalacheck" % "1.14.0",
  "info.debatty" % "java-string-similarity" % "1.0.0",
  "mysql" % "mysql-connector-java" % "6.0.6",
  "org.json4s" %% "json4s-native" % "3.5.3",
//  "graphframes"                 % "graphframes"              % "0.4.0-spark2.0-s_2.11",
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-literal" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "io.circe" %% "circe-generic-extras" % circeVersion,
  "io.circe" %% "circe-java8" % circeVersion,
  "com.datlinq" %% "scalafiniti" % "0.2.6",
  "org.mongodb.spark" %% "mongo-spark-connector" % "2.2.0",
  "com.databricks" %% "spark-xml" % "0.4.1",
  "com.thoughtworks.xstream" % "xstream" % "1.4.10",
//  "harsha2010" %% "magellan" % "1.0.7",
  "com.rockymadden.stringmetric" %%   "stringmetric-core" % "0.27.4",

"com.github.nscala-time" %% "nscala-time" % "2.16.0",
  "codes.reactive" %% "scala-time" % "0.4.1",
  "org.slf4j" % "slf4j-api" % slf4jVersion
  

).map(_.exclude("ch.qos.logback", "*"))

//libraryDependencies ++= Seq(
//  "org.apache.spark" %% "spark-core" % sparkVersion % "provided" ,
//  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided" ,
//  "com.holdenkarau" %% "spark-testing-base" % sparkVersion % "test"
//).map(_.exclude("org.slf4j", "*"))



scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Xlint:missing-interpolator",
  "-Ywarn-unused-import",
  "-Ywarn-unused",
  "-Ywarn-dead-code",
  "-language:_",
  "-encoding", "UTF-8"
)

