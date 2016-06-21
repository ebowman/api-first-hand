resolvers += Resolver.sonatypeRepo("releases")

addSbtPlugin("com.eed3si9n"     % "sbt-buildinfo" % "0.4.0")
addSbtPlugin("org.scoverage"    % "sbt-scoverage" % "1.3.5")
addSbtPlugin("me.lessis"        % "bintray-sbt"   % "0.2.1")

addSbtPlugin("org.scalariform"  % "sbt-scalariform" % "1.6.0")
addSbtPlugin("org.brianmckenna" % "sbt-wartremover"         % "0.13")
addSbtPlugin("org.scalastyle"   %% "scalastyle-sbt-plugin"  % "0.7.0")

libraryDependencies <+= (sbtVersion) { sv =>
  "org.scala-sbt" % "scripted-plugin" % sv
}



