resolvers += Resolver.bintrayIvyRepo("rallyhealth", "sbt-plugins")

addSbtPlugin("org.scalameta"       % "sbt-scalafmt"       % "2.5.2")
addSbtPlugin("com.github.sbt"      % "sbt-git"            % "2.0.1")
addSbtPlugin("com.rallyhealth.sbt" % "sbt-git-versioning" % "1.6.0")
addSbtPlugin("org.xerial.sbt"      % "sbt-sonatype"       % "3.10.0")
addSbtPlugin("com.github.sbt"      % "sbt-pgp"            % "2.2.1")
addSbtPlugin("org.typelevel"       % "sbt-tpolecat"       % "0.5.0")
