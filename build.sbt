name := "guice-test"

version := "1.0"

scalaVersion := "2.10.3"

fork := true

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

// UTILS

libraryDependencies += "com.google.guava" % "guava" % "15.0"

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "2.0.2"

// LOG

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"

libraryDependencies += "com.typesafe" %% "scalalogging-slf4j" % "1.0.1"

// GUICE

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.0.0-beta"