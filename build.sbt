organization := "org.openbrazil"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-http"  % "10.0.4",
  "com.github.melrief" %% "pureconfig" % "0.6.0",
  "technology.tabula"   % "tabula"     % "0.9.2"
)

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
