name := "guice-learning"

version := "1.0"

scalaVersion := "2.10.4"

fork := true

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

org.scalastyle.sbt.ScalastylePlugin.Settings

// utils

libraryDependencies += "com.google.guava" % "guava" % "16.0.1"

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "2.0.3"

// log

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.6"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.1"

libraryDependencies += "com.typesafe" %% "scalalogging-slf4j" % "1.1.0"

// test

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.2" % "test"

libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5" % "test"

// guice

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.0.0-beta"