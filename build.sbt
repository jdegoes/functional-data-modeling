val dottyVersion = "0.26.0"

lazy val root = project
  .in(file(""))
  .settings(
    name := "functional-data-modeling",
    version := "0.1.0",
    scalacOptions ++= Seq(
      "-language:postfixOps"
    ),
    scalaVersion := dottyVersion
  )
