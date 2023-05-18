name := """OnlineShop"""
organization := "com.praksa"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies += filters

libraryDependencies ++= Seq(
  // Database
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.1.0",
  "org.postgresql" % "postgresql" % "42.5.0",
  "com.github.jwt-scala" %% "jwt-play-json" % "9.2.0",
  "com.auth0" % "jwks-rsa" % "0.6.1"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.praksa.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.praksa.binders._"
