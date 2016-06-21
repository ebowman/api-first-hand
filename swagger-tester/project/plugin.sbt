lazy val root = Project("plugins", file(".")).dependsOn(swaggerPlugin)

lazy val swaggerPlugin = ProjectRef(Path.fileProperty("user.dir").getParentFile, "plugin")