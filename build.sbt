organization := "org.openbrazil"

scalaVersion := "2.12.1"

name := "c4c-gestao-br-crawler"

version := "0.0.1.DEV"

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-http"  % "10.0.4",
  "com.github.melrief"     %% "pureconfig" % "0.6.0",
  "technology.tabula"       % "tabula"     % "0.9.2",
  "org.scala-lang.modules" %% "scala-xml"  % "1.0.6",
  "org.ccil.cowan.tagsoup"  % "tagsoup"    % "1.2.1"
)

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
