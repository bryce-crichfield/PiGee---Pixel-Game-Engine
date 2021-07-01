name := "GameProjectSummer2021"

version := "0.1"

//scalaVersion := "2.13.5"
scalaVersion := "3.0"


idePackagePrefix := Some("org.apo")

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)


libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3"
)