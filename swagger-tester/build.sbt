lazy val root = (project in file("."))
  .enablePlugins(PlaySwagger, PlayScala)
  .dependsOn(swaggerApi, swaggerPlugin)

// The swagger plugin automatically adds a dependency on this, but we make it an
// explicit project ref, so that when we change it, we get the updates without
// having to publish it again
lazy val swaggerApi = ProjectRef(Path.fileProperty("user.dir").getParentFile, "api")

lazy val swaggerPlugin = ProjectRef(Path.fileProperty("user.dir").getParentFile, "plugin")

scalaVersion := "2.10.5"

routesGenerator := InjectedRoutesGenerator

libraryDependencies += specs2
